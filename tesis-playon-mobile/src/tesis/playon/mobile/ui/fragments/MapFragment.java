package tesis.playon.mobile.ui.fragments;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.Const.DbValues;
import tesis.playon.mobile.PlayonApp;
import tesis.playon.mobile.R;
import tesis.playon.mobile.model.GeoJSON;
import tesis.playon.mobile.model.Post;
import tesis.playon.mobile.provider.ParkingContract.PanelsCodes;
import tesis.playon.mobile.provider.ParkingContract.Posts;
import tesis.playon.mobile.provider.ParkingContract.PostsColumns;
import tesis.playon.mobile.ui.widgets.MyInfoWindowAdapter;
import tesis.playon.mobile.utils.ActivityHelper;
import tesis.playon.mobile.utils.GeoHelp;
import tesis.playon.mobile.utils.LongPressLocationSource;
import tesis.playon.mobile.utils.SearchMessageHandler;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockMapFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class MapFragment extends SherlockMapFragment implements SearchView.OnQueryTextListener,
	SearchMessageHandler.OnMessageHandledListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener,
	// MapListener,
	Runnable {
    protected static final String TAG = "MapFragment";

    protected OnMyLocationChangedListener mListener;

    protected static final int INDEX_OVERLAY_MY_LOCATION = 0x0;
    protected static final int INDEX_OVERLAY_PLACEMARKS = 0x1;

    // protected static final float ZOOM_DEFAULT = 12f;
    private static final float ZOOM_NEAR = 17f;
    private static final float ZOOM_MIN = 16f;
    private static final float HUE_MARKER = 94f;
    private static final float DISTANCE_MARKER_HINT = 50f;

    private GoogleMap mMap;
    private LongPressLocationSource mLongPressLocationSource;

    protected LocationManager mLocationManager;

    private Location mMapCenter = null;
    private LatLng screenCenter = null;
    private Marker clickedMarker = null;
    private Marker searchedMarker = null;
    private boolean hasHintMarker = true;

    ActivityHelper activityHelper;
    PlayonApp parkingApp;

    final private Handler handler = new SearchMessageHandler(this);
    private JsonAsyncTask jsonAsyncTask = null;
    private DbAsyncTask dbAsyncTask = null;

    private MenuItem searchItem;
    private String postalCode;

    /**
     * Container Activity must implement this interface to receive the list item clicks.
     */
    public interface OnMyLocationChangedListener {
	public void OnMyLocationChanged(Location location);

	public void OnMyMapClickListener();

	public void OnSearchClickListener();
    }

    /**
     * Attach a listener.
     */
    @Override
    public void onAttach(Activity activity) {
	super.onAttach(activity);
	try {
	    mListener = (OnMyLocationChangedListener) activity;
	} catch (ClassCastException e) {
	    throw new ClassCastException(activity.toString() + " must implement OnMyLocationChangedListener");
	}
    }

    // @Override
    public View onCreateViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	View root = super.onCreateView(inflater, container, savedInstanceState);

	return root;
    }

    /**
     * Initialize map and LocationManager
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);

	setHasOptionsMenu(true);

	activityHelper = ActivityHelper.createInstance(getActivity());
	parkingApp = (PlayonApp) getActivity().getApplicationContext();
	setUpMapIfNeeded();

	mLocationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(
		Context.LOCATION_SERVICE);

	initMap();
    }

    /**
     * Enable user location (GPS) updates on map display.
     */
    @Override
    public void onResume() {

	super.onResume();

	setUpMapIfNeeded();

	if (mLongPressLocationSource != null) {
	    mLongPressLocationSource.onResume();
	}
    }

    /**
     * Disable user location (GPS) updates on map hide.
     */
    @Override
    public void onPause() {

	super.onPause();

	if (mLongPressLocationSource != null) {
	    mLongPressLocationSource.onPause();
	}
    }

    /**
     * Create the fragment's Address Search MenuItem, from code.
     */
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	super.onCreateOptionsMenu(menu, inflater);

	// TWEAK: FC when inflated from XML
	searchItem = (MenuItem) menu.add(getResources().getString(R.string.menu_search));
	searchItem.setIcon(R.drawable.abs__ic_search);
	searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

	// Create the SearchView
	SearchView searchView = new SearchView(getActivity());
	searchView.setQueryHint(getResources().getString(R.string.search_hint));
	searchView.setOnQueryTextListener(this);
	// Collapse when focus lost
	searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
	    @Override
	    public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
		    mListener.OnSearchClickListener();
		} else {
		    searchItem.collapseActionView();
		}
	    }
	});

	// Assign the SearchView to the menuItem
	searchItem.setActionView(searchView);
    }

    @Override
    public void onMapClick(LatLng point) {
	clickedMarker = null;
	mListener.OnMyMapClickListener();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
	clickedMarker = marker;
	mListener.OnMyMapClickListener();
	return false;
    }

    /**
     * Handle the Address Search query
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
	postalCode = query;

	searchItem.collapseActionView();
	showDialogProcessing();

	return true;
    }

    /**
     * Required implementation
     */
    @Override
    public boolean onQueryTextChange(String newText) {
	// TODO: use for address suggestions or auto-complete
	return false;
    }

    /**
     * This runnable thread gets the Geocode search value in the background. Results are sent to the handler.
     */
    @Override
    public void run() {

	Location loc = null;
	try {
	    loc = GeoHelp.findLocatioFromName(getActivity(), postalCode);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	final Message msg = handler.obtainMessage();
	final Bundle b = new Bundle();

	if (loc == null) {
	    /**
	     * Send error message to handler.
	     */
	    b.putInt(Const.KEY_BUNDLE_SEARCH_ADDRESS, Const.BUNDLE_SEARCH_ADDRESS_ERROR);
	} else {
	    /**
	     * Send success message to handler with the found geo coordinates.
	     */
	    b.putInt(Const.KEY_BUNDLE_SEARCH_ADDRESS, Const.BUNDLE_SEARCH_ADDRESS_SUCCESS);
	    b.putDouble(Const.KEY_BUNDLE_ADDRESS_LAT, loc.getLatitude());
	    b.putDouble(Const.KEY_BUNDLE_ADDRESS_LNG, loc.getLongitude());
	}
	msg.setData(b);

	handler.sendMessage(msg);
    }

    /**
     * Handle the runnable thread results. This hides the indeterminate progress bar then centers map on found location
     * or displays error message.
     */
    @Override
    public void OnMessageHandled(Message msg) {
	((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(Boolean.FALSE);
	final Bundle b = msg.getData();

	if (b.getInt(Const.KEY_BUNDLE_SEARCH_ADDRESS) == Const.BUNDLE_SEARCH_ADDRESS_SUCCESS) {
	    /**
	     * Address is found, center map on location.
	     */
	    final Location location = new Location(Const.LOCATION_PROVIDER_SEARCH);
	    location.setLatitude(b.getDouble(Const.KEY_BUNDLE_ADDRESS_LAT));
	    location.setLongitude(b.getDouble(Const.KEY_BUNDLE_ADDRESS_LNG));

	    setMapCenterZoomed(location);

	    /**
	     * Add marker for found location
	     */

	    searchedMarker = mMap.addMarker(new MarkerOptions()
		    .position(new LatLng(location.getLatitude(), location.getLongitude())).title(postalCode)
		    .snippet(null).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
		    .visible(true));
	    searchedMarker.showInfoWindow();

	} else {
	    /**
	     * Address not found! Display error message.
	     */
	    final String errorMsg = String.format(getResources().getString(R.string.toast_search_error, postalCode));
	    ((PlayonApp) getActivity().getApplicationContext()).showToastText(errorMsg, Toast.LENGTH_LONG);
	}
    }

    private void setUpMapIfNeeded() {
	// Do a null check to confirm that we have not already instantiated the map.
	if (!checkReady()) {
	    // Try to obtain the map from the SupportMapFragment.
	    mMap = getMap();
	    // Check if we were successful in obtaining the map.
	    if (checkReady()) {
		setUpMap();
	    }
	}
    }

    private void setUpMap() {
	if (mLongPressLocationSource == null) {
	    mLongPressLocationSource = new LongPressLocationSource(mMap);
	    mMap.setOnMapLongClickListener(mLongPressLocationSource);
	}

	mMap.setMyLocationEnabled(true);
	mMap.setMapType(MAP_TYPE_NORMAL);

	mMap.setLocationSource(null);

	mMap.setInfoWindowAdapter(new MyInfoWindowAdapter(getActivity()));

	mMap.setOnMapClickListener(this);
	mMap.setOnMarkerClickListener(this);
	mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

	    @Override
	    public void onCameraChange(CameraPosition cameraPosition) {
		mListener.OnMyMapClickListener();
		updateOverlays();
	    }
	});

	UiSettings uiSettings = mMap.getUiSettings();

	uiSettings.setZoomControlsEnabled(false);
	uiSettings.setMyLocationButtonEnabled(true);
	uiSettings.setAllGesturesEnabled(true);
	uiSettings.setCompassEnabled(true);

    }

    private boolean checkReady() {
	if (mMap == null) {
	    parkingApp.showToastText(R.string.toast_map_not_ready, Toast.LENGTH_SHORT);
	    return false;
	}
	return true;
    }

    private class DbAsyncTask extends AsyncTask<Object, Void, Cursor> {

	@Override
	protected void onPreExecute() {

	    try {
		// Needed to avoid problems when main activity is sent to background
		((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(Boolean.TRUE);

	    } catch (NullPointerException e) {
		e.printStackTrace();
	    }
	}

	@Override
	protected Cursor doInBackground(Object... params) {
	    final int dayOfWeek = (Integer) params[0];
	    final double hourOfWeek = (Double) params[1] + (dayOfWeek - 1) * 24;
	    final int duration = (Integer) params[2];

	    final LatLng NE = (LatLng) params[3];
	    final LatLng SW = (LatLng) params[4];

	    final GregorianCalendar calendar = parkingApp.getParkingCalendar();
	    // API uses values 0-365 (or 364)
	    final int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR) - 1;

	    Cursor cur = getActivity()
		    .getApplicationContext()
		    .getContentResolver()
		    .query(Posts.CONTENT_ALLOWED_URI,
			    PostsOverlaysQuery.PROJECTION,
			    PostsColumns.LAT + " >= ? AND " + PostsColumns.LAT + " <= ? AND " + PostsColumns.LNG
				    + " >= ? AND " + PostsColumns.LNG + " <= ? ",

			    new String[] { Double.toString(SW.latitude), Double.toString(NE.latitude),
				    Double.toString(SW.longitude), Double.toString(NE.longitude),
				    Double.toString(hourOfWeek), Integer.toString(duration),
				    Integer.toString(dayOfYear) }, null);

	    return cur;
	}

	@Override
	protected void onPostExecute(Cursor cursor) {
	    try {
		// Needed to avoid problems when main activity is sent to background
		((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(Boolean.FALSE);

	    } catch (NullPointerException e) {
		e.printStackTrace();
		return;
	    }

	    if (cursor == null) {
		return;
	    }

	    final int totalMarkers = cursor.getCount();
	    if (totalMarkers == 0) {
		cursor.close();
		return;
	    }

	    if (isCancelled()) {
		cursor.close();
		return;
	    }

	    mMap.clear();

	    // TODO: use same following code between DB and JSON
	    if (searchedMarker != null) {
		searchedMarker = mMap.addMarker(new MarkerOptions().position(searchedMarker.getPosition())
			.title(searchedMarker.getTitle()).snippet(null)
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(true));
		searchedMarker.showInfoWindow();
	    }

	    if (screenCenter == null || clickedMarker != null) {
		hasHintMarker = false;
	    }
	    Location locationCenter = new Location(Const.LOCATION_PROVIDER_DEFAULT);
	    if (hasHintMarker) {
		locationCenter.setLatitude(screenCenter.latitude);
		locationCenter.setLongitude(screenCenter.longitude);
	    }

	    cursor.moveToFirst();
	    do {
		if (isCancelled()) {
		    return;
		}

		final double lat = cursor.getDouble(PostsOverlaysQuery.LAT);
		final double lng = cursor.getDouble(PostsOverlaysQuery.LNG);
		final String desc = cursor.getString(PostsOverlaysQuery.CONCAT_DESCRIPTION).replace(
			DbValues.CONCAT_SEPARATOR, Const.LINE_SEPARATOR);

		final Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).snippet(desc)
			.icon(BitmapDescriptorFactory.defaultMarker(HUE_MARKER)).visible(true));

		if (clickedMarker != null) {
		    if (clickedMarker.getPosition().equals(marker.getPosition())) {
			marker.showInfoWindow();
		    }
		} else if (hasHintMarker) {
		    Location locationMarker = new Location(Const.LOCATION_PROVIDER_DEFAULT);
		    locationMarker.setLatitude(marker.getPosition().latitude);
		    locationMarker.setLongitude(marker.getPosition().longitude);

		    if (locationCenter.distanceTo(locationMarker) < DISTANCE_MARKER_HINT) {
			marker.showInfoWindow();
			hasHintMarker = false;
			clickedMarker = marker;
		    }
		}

	    } while (cursor.moveToNext());
	    cursor.close();

	}

    }

    private class JsonAsyncTask extends AsyncTask<URL, Void, GeoJSON> {
	@SuppressWarnings("unused")
	protected static final String TAG = "JsonAsyncTask";

	@Override
	protected void onPreExecute() {

	    try {
		// Needed to avoid problems when main activity is sent to background
		((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(Boolean.TRUE);

	    } catch (NullPointerException e) {
		e.printStackTrace();
	    }
	}

	@Override
	protected GeoJSON doInBackground(URL... params) {

	    URL fetchUrl = params[0];

	    URLConnection urlConnection;
	    try {
		urlConnection = fetchUrl.openConnection();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	    }

	    try {
		urlConnection.connect();
	    } catch (IOException e) {
		e.printStackTrace();
		return null;
	    }
	    JsonReader reader;
	    try {
		reader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
	    } catch (IOException e) {
		e.printStackTrace();
		return null;
	    }
	    JsonParser parser = new JsonParser();
	    // TODO: surround with try/catch
	    JsonElement rootElement = parser.parse(reader);

	    if (rootElement != null) {
		Gson gson = new Gson();
		GeoJSON geoJson = gson.fromJson(rootElement, GeoJSON.class);
		return geoJson;
	    }

	    return null;
	}

	@Override
	protected void onPostExecute(GeoJSON geoJson) {

	    try {
		((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(Boolean.FALSE);

	    } catch (NullPointerException e) {
		e.printStackTrace();
		return;
	    }

	    if (geoJson == null || geoJson.getFeatures() == null) {
		return;
	    }

	    final int totalMarkers = geoJson.getFeatures().size();

	    if (isCancelled()) {
		return;
	    }

	    mMap.clear();

	    // TODO: use same following code between DB and JSON
	    if (searchedMarker != null) {
		searchedMarker = mMap.addMarker(new MarkerOptions().position(searchedMarker.getPosition())
			.title(searchedMarker.getTitle()).snippet(null)
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(true));
		searchedMarker.showInfoWindow();
	    }

	    if (screenCenter == null || clickedMarker != null) {
		hasHintMarker = false;
	    }
	    Location locationCenter = new Location(Const.LOCATION_PROVIDER_DEFAULT);
	    if (hasHintMarker) {
		locationCenter.setLatitude(screenCenter.latitude);
		locationCenter.setLongitude(screenCenter.longitude);
	    }

	    for (int i = 0; i < totalMarkers; i++) {

		if (isCancelled()) {
		    return;
		}

		Post post = geoJson.getFeatures().get(i);

		final Marker marker = mMap.addMarker(new MarkerOptions().position(post.getLatLng())
			.snippet(post.getDesc()).icon(BitmapDescriptorFactory.defaultMarker(HUE_MARKER)).visible(true));

		if (clickedMarker != null) {
		    if (clickedMarker.getPosition().equals(marker.getPosition())) {
			marker.showInfoWindow();
		    }
		} else if (hasHintMarker) {
		    Location locationMarker = new Location(Const.LOCATION_PROVIDER_DEFAULT);
		    locationMarker.setLatitude(marker.getPosition().latitude);
		    locationMarker.setLongitude(marker.getPosition().longitude);

		    if (locationCenter.distanceTo(locationMarker) < DISTANCE_MARKER_HINT) {
			marker.showInfoWindow();
			hasHintMarker = false;
			clickedMarker = marker;
		    }
		}
	    }
	}
    }

    public void updateOverlaysForced() {
	screenCenter = null;
	updateOverlays();
    }

    private void updateOverlays() {

	if (mMap.getCameraPosition().zoom < ZOOM_MIN) {
	    parkingApp.showToastText(R.string.toast_map_zoom_to_update, Toast.LENGTH_LONG);
	    return;
	}

	final Projection projection = mMap.getProjection();

	if (screenCenter == null) {
	    screenCenter = mMap.getCameraPosition().target;
	} else {
	    // TODO: get screen center without repeated use of Projection
	    final LatLng cameraTarget = mMap.getCameraPosition().target;
	    final Point cameraCenterPoint = projection.toScreenLocation(cameraTarget);
	    final int screenWidth = cameraCenterPoint.x * 2;
	    final int screenHeight = cameraCenterPoint.y * 2;

	    final Point oldCenterPoint = projection.toScreenLocation(screenCenter);

	    if ((oldCenterPoint.x > screenWidth * 0.75) || (oldCenterPoint.x < screenWidth * 0.25)
		    || (oldCenterPoint.y > screenHeight * 0.75) || (oldCenterPoint.y < screenHeight * 0.25)) {
		screenCenter = cameraTarget;
	    } else {
		return;
	    }
	}

	final VisibleRegion region = projection.getVisibleRegion();
	final LatLngBounds bounds = region.latLngBounds;
	final LatLng SW = bounds.southwest;
	final LatLng NE = bounds.northeast;

	// Get arguments for API call
	final GregorianCalendar parkingCalendar = parkingApp.getParkingCalendar();
	final int day = (parkingCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 7 : parkingCalendar
		.get(Calendar.DAY_OF_WEEK) - 1);
	final double parkingHour = parkingCalendar.get(Calendar.HOUR_OF_DAY)
		+ Math.round(parkingCalendar.get(Calendar.MINUTE) / 0.6) / 100.00d;
	final int duration = parkingApp.getParkingDuration();

	if (Const.HAS_OFFLINE) {
	    queryOverlays(day, parkingHour, duration, NE, SW);
	} else {
	    downloadOverlays(day, parkingHour, duration, NE, SW);
	}

    }

    private void queryOverlays(int day, double parkingHour, int duration, LatLng NE, LatLng SW) {
	if (dbAsyncTask != null) {
	    dbAsyncTask.cancel(true);
	}

	dbAsyncTask = new DbAsyncTask();
	dbAsyncTask.execute(day, parkingHour, duration, NE, SW);
    }

    private void downloadOverlays(int day, double parkingHour, int duration, LatLng NE, LatLng SW) {
	final String fetchUrl = String.format(Const.Api.POSTS_LIVE, day, parkingHour, duration, NE.latitude,
		SW.longitude, SW.latitude, NE.longitude);

	URL apiURL = null;
	try {
	    apiURL = new URL(fetchUrl);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	    return;
	}

	if (jsonAsyncTask != null) {
	    jsonAsyncTask.cancel(true);
	}
	jsonAsyncTask = new JsonAsyncTask();
	jsonAsyncTask.execute(apiURL);
    }

    /**
     * Initialize Map: centre and load posts
     */
    protected void initMap() {

    }

    /**
     * Set new map center.
     * 
     * @param mapCenter
     */
    protected void animateToPoint(Location mapCenter) {
	if (mMap == null) {
	    return;
	}
	if (mapCenter != null) {
	    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
		    new LatLng(mapCenter.getLatitude(), mapCenter.getLongitude()), ZOOM_NEAR));
	} else {
	    mMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_NEAR));
	    initialAnimateToPoint();
	}
    }

    /**
     * Initial map center animation on detected user location. If user is more than minimum-distance from the city,
     * center the map on Downtown. Also defines the zoom.
     */
    protected void initialAnimateToPoint() {
	final List<String> enabledProviders = mLocationManager.getProviders(true);

	double coordinates[] = Const.MAPS_DEFAULT_COORDINATES;
	final double lat = coordinates[0];
	final double lng = coordinates[1];

	final Location userLocation = parkingApp.getLocation();
	if (userLocation != null) {
	    /**
	     * Center on app's user location.
	     */
	    mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(userLocation.getLatitude(), userLocation
		    .getLongitude())));
	} else {
	    /**
	     * Center on Downtown.
	     */
	    mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
	}

	if ((mMapCenter == null) && enabledProviders.contains(LocationManager.NETWORK_PROVIDER)) {
	} else if (mMapCenter != null) {
	    mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(mMapCenter.getLatitude(), mMapCenter
		    .getLongitude())));
	}
    }

    /**
     * Setter for the MapCenter GeoPoint. Centers map on the new location and displays the ViewBallooon.
     * 
     * @param mapCenter
     *            The new location
     */
    public void setMapCenter(Location mapCenter) {
	animateToPoint(mapCenter);
    }

    /**
     * Sets the map center on the user real location with a near zoom. Used for Tab re-selection.
     */
    public void resetMapCenter() {
	searchedMarker = null;
	if (!mMap.isMyLocationEnabled()) {
	    mMap.setMyLocationEnabled(true);
	}
	mMap.setLocationSource(null);
	setMapCenterZoomed(parkingApp.getLocation());
    }

    /**
     * Sets the map center on the location with a near zoom. Used for Address Search and Tab re-selection.
     */
    private void setMapCenterZoomed(Location mapCenter) {
	// mMapController.setZoom(ZOOM_NEAR);
	setMapCenter(mapCenter);
    }

    /**
     * Called from the activity to handle onKeyUp
     */
    public void searchToggle(boolean isDisplayed) {
	if (isVisible() && isDisplayed) {
	    searchItem.expandActionView();
	} else {
	    searchItem.collapseActionView();
	}
    }

    /**
     * Show the Indeterminate ProgressBar and start the Geocode search thread.
     */
    protected void showDialogProcessing() {
	((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(Boolean.TRUE);

	final Thread thread = new Thread(this);
	thread.start();
    }

    private static interface PostsOverlaysQuery {

	final String[] PROJECTION = new String[] { Posts.ID_POST, Posts.LAT, Posts.LNG, PanelsCodes.CONCAT_DESCRIPTION };
	final int LAT = 1;
	final int LNG = 2;
	final int CONCAT_DESCRIPTION = 3;
    }
}
