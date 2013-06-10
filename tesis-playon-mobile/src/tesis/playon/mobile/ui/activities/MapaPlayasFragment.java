package tesis.playon.mobile.ui.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.GoogleGeoCodeResponse;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Playas;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MapaPlayasFragment extends Fragment implements OnMarkerClickListener {

    private final static String TAG = "MapaPlayasFragment";

    private static final String URL_PLAYAS = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/playas";

    private Playas playas;

    private MapView mMapView;

    private GoogleMap mMap;

    private String query;

    private GoogleGeoCodeResponse result;

    private PreferenceHelper mPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	Log.d(TAG, "onCreateView");

	// busco lista de playas
	mPreference = new PreferenceHelper(getActivity());
	query = mPreference.getQuery();

	if (null != query) {
	    query = query + ", Córdoba, Argentina";
	}

	new BuscarPlayaService().execute();

	View inflatedView = inflater.inflate(R.layout.mapa_playas, container, false);

	try {
	    MapsInitializer.initialize(getActivity());
	} catch (GooglePlayServicesNotAvailableException e) {
	    Log.d(TAG, "Google Play Store no instalado.");
	}

	mMapView = (MapView) inflatedView.findViewById(R.id.map);
	mMapView.onCreate(savedInstanceState);

	mMap = mMapView.getMap();
	mMap.setOnMarkerClickListener(this);
	return inflatedView;
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

    @Override
    public boolean onMarkerClick(Marker arg0) {

	if (arg0.isInfoWindowShown()) {
	    String nomPlaya = arg0.getTitle();

	    Bundle playa = new Bundle();
	    playa.putString(Const.NOMBRE_PLAYA, nomPlaya);

	    Fragment fragment = new DetallePlayaFragment();
	    fragment.setArguments(playa);

	    getActivity().getActionBar().getTabAt(2).setTabListener(new MyTabsListener(fragment));
	    getActivity().getActionBar().selectTab(getActivity().getActionBar().getTabAt(2));

	    return true;
	}
	return false;
    }

    private void llenarMapa(Playas playas) {

	Log.d(TAG, "llenarLista");
	mMap = mMapView.getMap();
	for (Playa playa : playas.getPlayas()) {
	    mMap.addMarker(
		    new MarkerOptions().position(new LatLng(playa.getLatitud(), playa.getLongitud()))
			    .title(playa.getNombreComercial()).snippet("Disponibilidad: " + playa.getDisponibilidad()))
		    .showInfoWindow();
	    LatLng latLng = new LatLng(playa.getLatitud(), playa.getLongitud());
	    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
	    mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
	}
    }

    class BuscarPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_PLAYAS;
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    playas = gson.fromJson(reader, Playas.class);
	    return playas.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.playas", playas);
	    result.putExtras(bundle);
	    if (null != query)
		new BuscarCoordenadasService().execute();
	    else {
		playas = new Utils().buscarPlaya(playas, mPreference.getLat(), mPreference.getLng(), 10);
		llenarMapa(playas);
	    }
	}
    }

    private String jsonCoord(String address) throws IOException {
	URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false");
	URLConnection connection = url.openConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	String jsonResult = "";
	while ((inputLine = in.readLine()) != null) {
	    jsonResult += inputLine;
	}
	in.close();
	return jsonResult;
    }

    class BuscarCoordenadasService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    Gson gson = new Gson();
	    result = null;
	    try {
		result = gson.fromJson(jsonCoord(URLEncoder.encode(query, "UTF-8")), GoogleGeoCodeResponse.class);
	    } catch (JsonSyntaxException e) {
		e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return null;
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    playas = new Utils().buscarPlaya(playas, result, 10);
	    llenarMapa(playas);
	}
    }

}