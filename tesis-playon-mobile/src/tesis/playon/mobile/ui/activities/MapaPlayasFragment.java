package tesis.playon.mobile.ui.activities;

import tesis.playon.mobile.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaPlayasFragment extends Fragment {

    private final static String TAG = "MapaPlayasFragment";

    private MapView mMapView;

    private GoogleMap mMap;

    private Bundle mBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mBundle = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	View inflatedView = inflater.inflate(R.layout.mapa_playas, container, false);

	try {
	    MapsInitializer.initialize(getActivity());
	} catch (GooglePlayServicesNotAvailableException e) {
	    Log.d(TAG, "Google Play Store no instalado.");
	}

	mMapView = (MapView) inflatedView.findViewById(R.id.map);
	mMapView.onCreate(mBundle);
	setUpMapIfNeeded(inflatedView);

	return inflatedView;

	// if (ConnectionResult.SUCCESS == GooglePlayServicesUtil.isGooglePlayServicesAvailable(appContext)) {
	// map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	// map.setMyLocationEnabled(true);
	//
	// // Getting LocationManager object from System Service LOCATION_SERVICE
	// LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	//
	// // Creating a criteria object to retrieve provider
	// Criteria criteria = new Criteria();
	//
	// // Getting the name of the best provider
	// String provider = locationManager.getBestProvider(criteria, true);
	//
	// // Getting Current Location
	// Location location = locationManager.getLastKnownLocation(provider);
	//
	// if (location != null) {
	// onLocationChanged(location);
	// }
	// }
    }

    // @Override
    // public void onLocationChanged(Location location) {
    //
    // // Getting latitude of the current location
    // double latitude = location.getLatitude();
    //
    // // Getting longitude of the current location
    // double longitude = location.getLongitude();
    //
    // // Creating a LatLng object for the current location
    // LatLng latLng = new LatLng(latitude, longitude);
    //
    // // Showing the current location in Google Map
    // map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    //
    // // Zoom in the Google Map
    // map.animateCamera(CameraUpdateFactory.zoomTo(15));
    // }

    private void setUpMapIfNeeded(View inflatedView) {
	if (mMap == null) {
	    mMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
	    if (mMap != null) {
		setUpMap();
	    }
	}
    }

    private void setUpMap() {
	mMap.addMarker(new MarkerOptions().position(new LatLng(54, 32)).title("Marker"));
    }

    @Override
    public void onResume() {
	super.onResume();
	mMapView.onResume();
    }

    @Override
    public void onPause() {
	super.onPause();
	mMapView.onPause();
    }

    @Override
    public void onDestroy() {
	mMapView.onDestroy();
	super.onDestroy();
    }
}