package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Cliente;
import tesis.playon.mobile.json.model.CuentaCliente;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

public class PerfilClienteActivity extends Activity {

    private static final String TAG = "PerfilUsuarioActivity";

    private static final String URL_PERFIL_CLIENTE = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/cliente/";

    private static final String URL_CUENTA_CLIENTE = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/cuentacliente/";

    private Cliente cliente;

    private CuentaCliente cuentaCliente;

    private Integer nroUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {

	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);

	PreferenceHelper mPreferences = new PreferenceHelper(getApplicationContext());
	nroUsuario = mPreferences.getIdUsuario();

	if (0 != nroUsuario) {
	    Log.d(TAG, "ID de usuario: " + nroUsuario);
	    if (Utils.isOnline(getApplicationContext())) {
		new PerfilClienteService().execute();
	    } else {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Problema de conexión").setMessage("No está conectado a Internet")
			.setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int id) {
				PerfilClienteActivity.this.finish();
			    }
			});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	    }

	    setContentView(R.layout.perfil_cliente);
	} else {
	    setContentView(R.layout.perfil_cliente_vacio);
	}

    }

    private void cargarPerfilCliente(CuentaCliente cuentaCliente) {

	Log.d(TAG, "cargarPerfilCliente");

	TextView nomCliente = (TextView) findViewById(R.id.txt_cliente);
	TextView documento = (TextView) findViewById(R.id.txt_documento);
	TextView domicilio = (TextView) findViewById(R.id.txt_domicilio);
	TextView nroCuenta = (TextView) findViewById(R.id.txt_nro_cuenta);
	TextView saldoCuenta = (TextView) findViewById(R.id.txt_saldo);
	TextView telefono = (TextView) findViewById(R.id.txt_telefono);
	TextView email = (TextView) findViewById(R.id.txt_email_cliente);

	nomCliente.setText(cliente.getUsuario().getNombre() + " " + cliente.getUsuario().getApellido());
	documento.setText(cliente.getUsuario().getTipoDoc().getNombre() + " " + cliente.getUsuario().getNroDoc());
	domicilio.setText(cliente.getDomicilio());
	if (cuentaCliente != null) {
	    nroCuenta.setText(cuentaCliente.getNroCuenta().toString());
	    saldoCuenta.setText("AR $" + cuentaCliente.getSaldo());
	} else {
	    nroCuenta.setText("N/A");
	    saldoCuenta.setText("N/A");
	}
	telefono.setText(cliente.getTelefono());
	if (cliente.getUsuario().getEmail() != null) {
	    email.setText(cliente.getUsuario().getEmail());
	} else {
	    email.setText("N/A");
	}
    }

    class PerfilClienteService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    String url = URL_PERFIL_CLIENTE + nroUsuario;
	    try {
		InputStream source = new Utils().retrieveStream(url);
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(source);
		cliente = gson.fromJson(reader, Cliente.class);
		return cliente.toString();
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	    return null;
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.cliente", cliente);
	    result.putExtras(bundle);
	    new CuentaClienteService().execute();
	}
    }

    class CuentaClienteService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    String url = URL_CUENTA_CLIENTE + cliente.getNroCliente();
	    try {
		InputStream source = new Utils().retrieveStream(url);
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(source);
		cuentaCliente = gson.fromJson(reader, CuentaCliente.class);
		return cuentaCliente.toString();
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	    return null;
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.cuentacliente", cuentaCliente);
	    result.putExtras(bundle);
	    cargarPerfilCliente(cuentaCliente);
	}
    }
}