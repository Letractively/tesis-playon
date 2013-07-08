package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.PerfilPlaya;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class DetallePlayaFragment extends Fragment implements OnClickListener {

    final static private String TAG = "PlayaFragment";

    private static final String URL_DETALLE_PLAYA = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/playa/";

    private static final String URL_PERFIL_PLAYA = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/perfilplaya/";

    private Bundle mBundle;

    private View mView;

    private Button tarifas;

    private Button promociones;

    private Button comentarios;

    private String nomPlaya;

    private Playa playa;

    private PerfilPlaya perfilPlaya;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	mBundle = getArguments();

	if (null != mBundle) {
	    nomPlaya = mBundle.getString(Const.NOMBRE_PLAYA);
	    PreferenceHelper mPreferences = new PreferenceHelper(getActivity());
	    mPreferences.updateNomPlaya(nomPlaya);
	    Log.d(TAG, "Nombre de la playa: " + nomPlaya);

	    if (Utils.isOnline(getActivity().getApplicationContext())) {
		new BuscarDetallePlayaService().execute();
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

	    mView = inflater.inflate(R.layout.playa_desc, container, false);

	} else {
	    mView = inflater.inflate(R.layout.playa_desc_vacia, container, false);
	}
	return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	super.onCreateOptionsMenu(menu, inflater);
	inflater.inflate(R.layout.menu_playas, menu);
    }

    @Override
    public void onClick(View v) {
	if (v == tarifas) {
	    Intent myIntent = new Intent(getActivity(), TarifasActivity.class);
	    getActivity().startActivity(myIntent);
	} else if (v == promociones) {
	    Intent myIntent = new Intent(getActivity(), PromocionesActivity.class);
	    getActivity().startActivity(myIntent);
	} else if (v == comentarios) {
	    Intent myIntent = new Intent(getActivity(), ComentariosActivity.class);
	    getActivity().startActivity(myIntent);
	}
    }

    private void cargarDetallePlaya(Playa playa, PerfilPlaya perfilPlaya) {

	Log.d(TAG, "cargarDetallePlaya");

	ImageView imagen = (ImageView) mView.findViewById(R.id.photo_playa);
	TextView nombre = (TextView) mView.findViewById(R.id.txt_nombre);
	TextView desc = (TextView) mView.findViewById(R.id.txt_desc);
	TextView direccion = (TextView) mView.findViewById(R.id.txt_direccion);
	TextView disponibilidad = (TextView) mView.findViewById(R.id.txt_disponibilidad);
	TextView telefono = (TextView) mView.findViewById(R.id.txt_telefono);
	TextView email = (TextView) mView.findViewById(R.id.txt_email);
	tarifas = (Button) mView.findViewById(R.id.btn_tarifas);
	promociones = (Button) mView.findViewById(R.id.btn_promociones);
	comentarios = (Button) mView.findViewById(R.id.btn_comentarios);

	// TODO: set image
	if (playa.getNombreComercial().equals("New Parking")) {
	    imagen.setImageResource(R.drawable.perfil_playa_hc);
	}

	nombre.setText(playa.getNombreComercial());
	desc.setText(perfilPlaya.getDescripcion());
	direccion.setText(playa.getDomicilio());
	disponibilidad.setText(playa.getDisponibilidad().toString());
	telefono.setText((null != playa.getTelefono()) ? playa.getTelefono() : Const.SIN_DATOS);
	email.setText((null != playa.getEmail()) ? playa.getEmail() : Const.SIN_DATOS);
	tarifas.setOnClickListener(this);
	promociones.setOnClickListener(this);
	comentarios.setOnClickListener(this);
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
	    new BuscarPerfilPlayaService().execute();
	}
    }

    class BuscarPerfilPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_PERFIL_PLAYA
		    + nomPlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    perfilPlaya = gson.fromJson(reader, PerfilPlaya.class);
	    return perfilPlaya.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.perfilplaya", perfilPlaya);
	    result.putExtras(bundle);
	    cargarDetallePlaya(playa, perfilPlaya);
	}
    }

}