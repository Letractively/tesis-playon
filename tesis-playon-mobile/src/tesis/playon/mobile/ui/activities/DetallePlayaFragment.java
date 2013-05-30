package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Tarifas;
import tesis.playon.mobile.utils.Utils;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

public class DetallePlayaFragment extends Fragment {

    final static private String TAG = "PlayaFragment";

    private static final String URL_DETALLE_PLAYA = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/playa/";

    private static final String URL_TARIFAS_VIGENTES_PLAYA = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/tarifas/";

    private Bundle mBundle;

    private View mView;

    private Playa playa;

    private String nomPlaya;

    private Tarifas tarifas;

    // private MapView mMapView;
    //
    // private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	mBundle = getArguments();

	if (null != mBundle) {
	    nomPlaya = mBundle.getString(Const.NOMBRE_PLAYA);
	    Log.d(TAG, "Nombre de la playa: " + nomPlaya);
	    new BuscarDetallePlayaService().execute();
	    mView = inflater.inflate(R.layout.playa_desc, container, false);

	    // try {
	    // MapsInitializer.initialize(getActivity());
	    // } catch (GooglePlayServicesNotAvailableException e) {
	    // Log.d(TAG, "Google Play Store no instalado.");
	    // }
	    // mMapView = (MapView) mView.findViewById(R.id.map_playa);
	    // mMapView.onCreate(mBundle);
	    // setUpMapIfNeeded(mView);
	} else {
	    mView = inflater.inflate(R.layout.playa_desc_vacia, container, false);
	}
	return mView;
    }

    // private void setUpMapIfNeeded(View inflatedView) {
    // if (mMap == null) {
    // mMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
    // if (mMap != null) {
    // setUpMap();
    // }
    // }
    // }

    // private void setUpMap() {
    // mMap.addMarker(new MarkerOptions().position(new LatLng(54, 32)).title("Marker"));
    // }

    private void cargarDetallePlaya(Playa playa, Tarifas tarifas) {

	Log.d(TAG, "cargarDetallePlaya");

	TextView nombre = (TextView) mView.findViewById(R.id.txt_nombre);
	TextView direccion = (TextView) mView.findViewById(R.id.txt_direccion);
	TextView disponibilidad = (TextView) mView.findViewById(R.id.txt_disponibilidad);
	TextView telefono = (TextView) mView.findViewById(R.id.txt_telefono);
	TextView email = (TextView) mView.findViewById(R.id.txt_email);

	nombre.setText(playa.getNombreComercial());
	direccion.setText(playa.getDomicilio());
	disponibilidad.setText(playa.getDisponibilidad().toString());
	telefono.setText((null != playa.getTelefono()) ? playa.getTelefono() : Const.SIN_DATOS);
	email.setText((null != playa.getEmail()) ? playa.getEmail() : Const.SIN_DATOS);
    }

    class BuscarDetallePlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_DETALLE_PLAYA
		    + nomPlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    playa = gson.fromJson(reader, Playa.class);
	    return playa.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.playa", playa);
	    result.putExtras(bundle);
	    // cargarDetallePlaya(playa);
	    new BuscarTarifasVigentesPlayaService().execute();
	}
    }

    class BuscarTarifasVigentesPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_TARIFAS_VIGENTES_PLAYA
		    + nomPlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    tarifas = gson.fromJson(reader, Tarifas.class);
	    return tarifas.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.tarifas", tarifas);
	    result.putExtras(bundle);
	    cargarDetallePlaya(playa, tarifas);
	}
    }
}