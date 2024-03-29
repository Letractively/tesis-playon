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
import tesis.playon.mobile.json.model.PerfilPlaya;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Playas;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MapaPlayasFragment extends Fragment implements OnInfoWindowClickListener {

    private final static String TAG = "MapaPlayasFragment";

    private static final String URL_PLAYAS = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/playas";

    private static final String URL_PERFIL_PLAYA = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/perfilplaya/";

    private Playas playas;

    private MapView mMapView;

    private GoogleMap mMap;

    private String query;

    private GoogleGeoCodeResponse result;

    private PreferenceHelper mPreference;

    private Double latActual;

    private Double lngActual;

    private String nombrePlaya;

    private PerfilPlaya perfilPlaya;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	Log.d(TAG, "onCreateView");

	mPreference = new PreferenceHelper(getActivity());
	query = mPreference.getQuery();

	if (null != query) {
	    query = query + ", Córdoba, Argentina";
	}

	if (Utils.isOnline(getActivity().getApplicationContext())) {
	    new BuscarPlayaService().execute();
	} else {
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
	    alertDialogBuilder.setTitle("Problema de conexión").setMessage("No está conectado a Internet")
		    .setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			    getActivity().finish();
			}
		    });
	    AlertDialog alertDialog = alertDialogBuilder.create();
	    alertDialog.show();
	}

	View inflatedView = inflater.inflate(R.layout.mapa_playas, container, false);

	try {
	    MapsInitializer.initialize(getActivity());
	} catch (GooglePlayServicesNotAvailableException e) {
	    Log.d(TAG, "Google Play Store no instalado.");
	}

	mMapView = (MapView) inflatedView.findViewById(R.id.map);
	mMapView.onCreate(savedInstanceState);

	mMap = mMapView.getMap();
	mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
	mMap.setOnInfoWindowClickListener(this);
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
    public void onInfoWindowClick(Marker arg0) {

	if (!arg0.getTitle().equalsIgnoreCase("Usted esta aquí")) {
	    String nomPlaya = arg0.getTitle();
	    Bundle mPlaya = new Bundle();
	    mPlaya.putString(Const.NOMBRE_PLAYA, nomPlaya);

	    Fragment fragment = new DetallePlayaFragment();
	    fragment.setArguments(mPlaya);

	    getActivity().getActionBar().getTabAt(2).setTabListener(new MyTabsListener(fragment));
	    getActivity().getActionBar().selectTab(getActivity().getActionBar().getTabAt(2));
	}
    }

    private void llenarMapa(Playas playas) {

	Log.d(TAG, "llenarLista");
	mMap = mMapView.getMap();
	if (null != query) {
	    latActual = Double.parseDouble(result.results[0].geometry.location.lat);
	    lngActual = Double.parseDouble(result.results[0].geometry.location.lng);
	} else {
	    latActual = Double.parseDouble(mPreference.getLat());
	    lngActual = Double.parseDouble(mPreference.getLng());
	}
	LatLng latLng = new LatLng(latActual, lngActual);
	mMap.addMarker(
		new MarkerOptions().position(latLng).title("Usted esta aquí")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.mapa_auto))).showInfoWindow();
	mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
	mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

	// draw search results
	for (Playa playa : playas.getPlayas()) {

	    mMap.addMarker(new MarkerOptions().position(new LatLng(playa.getLatitud(), playa.getLongitud()))
		    .title(playa.getNombreComercial()).snippet("Disponibilidad: " + playa.getDisponibilidad())
		    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapa_playa_48)));
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

    class BuscarPerfilPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    String url = URL_PERFIL_PLAYA
		    + nombrePlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    perfilPlaya = gson.fromJson(reader, PerfilPlaya.class);
	    cancel(true);
	    return perfilPlaya.toString();
	}

    }

    class MyInfoWindowAdapter implements InfoWindowAdapter {

	private final View myContentsView;

	MyInfoWindowAdapter() {
	    myContentsView = getActivity().getLayoutInflater().inflate(R.layout.custom_info_window, null);
	}

	@Override
	public View getInfoContents(Marker marker) {

	    TextView tvNomPlaya = ((TextView) myContentsView.findViewById(R.id.nombre_playa_info));
	    tvNomPlaya.setText(marker.getTitle());
	    TextView tvDispPlaya = ((TextView) myContentsView.findViewById(R.id.disp_playa_info));
	    tvDispPlaya.setText(marker.getSnippet());

	    nombrePlaya = tvNomPlaya.getText().toString();

	    BuscarPerfilPlayaService mFotoPerfilPlaya = new BuscarPerfilPlayaService();
	    mFotoPerfilPlaya.execute();

	    while (!mFotoPerfilPlaya.isCancelled()) {

	    }

	    ImageView imagen = (ImageView) myContentsView.findViewById(R.id.foto_playa_info);

	    if (null != perfilPlaya && null != perfilPlaya.getFotoPerfil()) {

		byte[] decodedString = Base64.decode(perfilPlaya.getFotoPerfil(), Base64.DEFAULT);
		Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		if (bitmap != null) {
		    Drawable image = new BitmapDrawable(getActivity().getApplicationContext().getResources(),
			    Bitmap.createScaledBitmap(bitmap, 100, 100, true));
		    imagen.setImageDrawable(image);
		}
	    } else if (nombrePlaya.equals("Usted esta aquí")) {
		imagen.setImageResource(R.drawable.mapa_auto);
	    } else {
		imagen.setImageResource(R.drawable.mapa_playa);
	    }

	    return myContentsView;
	}

	@Override
	public View getInfoWindow(Marker marker) {
	    return null;
	}

    }

}