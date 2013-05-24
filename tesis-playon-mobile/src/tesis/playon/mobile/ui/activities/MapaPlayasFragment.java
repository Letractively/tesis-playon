package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class MapaPlayasFragment extends Fragment {

    private final static String TAG = "MapaPlayasFragment";

    private static final String URL_PLAYAS = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/playas";

    private Playas playas;

    private MapView mMapView;

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	Log.d(TAG, "onCreateView");

	// busco lista de playas
	PreferenceHelper mPreference = new PreferenceHelper(getActivity().getApplicationContext());
	String query = mPreference.getQuery();
	handleQuery(query);

	View inflatedView = inflater.inflate(R.layout.mapa_playas, container, false);

	try {
	    MapsInitializer.initialize(getActivity());
	} catch (GooglePlayServicesNotAvailableException e) {
	    Log.d(TAG, "Google Play Store no instalado.");
	}

	mMapView = (MapView) inflatedView.findViewById(R.id.map);
	mMapView.onCreate(savedInstanceState);

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

    private void handleQuery(String query) {

	Log.d(TAG, "handleQuery");
	doSearch(query);
	new BuscarPlayaService().execute();
    }

    private void doSearch(String queryStr) {
	// get a Cursor, prepare the ListAdapter and set it
    }

    private void llenarMapa(ArrayList<Playa> playas) {

	Log.d(TAG, "llenarLista");
	mMap = mMapView.getMap();
	for (Playa playa : playas) {
	    mMap.addMarker(new MarkerOptions().position(new LatLng(playa.getLatitud(), playa.getLongitud()))
		    .title(playa.getNombreComercial()).snippet("Disponibilidad: " + playa.getDisponibilidad()));
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
	    for (Playa playa : playas.getPlayas()) {
		Log.d(TAG, "Playa: " + playa.getRazonSocial() + " Dirección: " + playa.getDomicilio());
	    }
	    llenarMapa((ArrayList<Playa>) playas.getPlayas());
	}
    }

}