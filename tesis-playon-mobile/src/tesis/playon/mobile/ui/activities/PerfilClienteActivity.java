package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Cliente;
import tesis.playon.mobile.utils.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

public class PerfilClienteActivity extends Activity {

    private static final String TAG = "PerfilUsuarioActivity";

    private static final String URL_PERFIL_CLIENTE = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/cliente/";

    private Cliente cliente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.perfil_cliente);
	new PerfilClienteService().execute();
    }

    private void cargarPerfilCliente(Cliente cliente) {

	Log.d(TAG, "cargarPerfilCliente");
    }

    class PerfilClienteService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    String url = URL_PERFIL_CLIENTE + 3;
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    cliente = gson.fromJson(reader, Cliente.class);
	    return cliente.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.cliente", cliente);
	    result.putExtras(bundle);
	    cargarPerfilCliente(cliente);
	}
    }
}