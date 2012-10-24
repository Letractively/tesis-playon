package tesis.playon.mobile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Playas;
import tesis.playon.mobile.util.Utils;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

public class BuscarPlayasActivity extends ListActivity {

    private final static String LOG_TAG = "BuscarPlayasActivity";

    private static final String URL_PLAYAS = "http://10.0.2.2:8080/tesis-playon-restful/playas";

    private Playas playas;

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	handleIntent(getIntent());
    }

    public void onNewIntent(Intent intent) {
	setIntent(intent);
	handleIntent(intent);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
	// call detail activity for clicked entry
    }

    private void handleIntent(Intent intent) {
	if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	    String query = intent.getStringExtra(SearchManager.QUERY);
	    Log.d(LOG_TAG, "Direccion ingresada: " + query);
	    doSearch(query);
	    new BuscarPlayaService().execute();
	}
    }

    private void doSearch(String queryStr) {
	// get a Cursor, prepare the ListAdapter and set it
    }

    class BuscarPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    String url = URL_PLAYAS;
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    playas = gson.fromJson(reader, Playas.class);
	    return playas.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(LOG_TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.playas", playas);
	    result.putExtras(bundle);
	    // List<Playa> listaPlayas = playas.getPlayas();
	    for (Playa playa : playas.getPlayas()) {
		Log.d(LOG_TAG, "Playa: " + playa.getRazonSocial() + " Dirección: " + playa.getDomicilio());
	    }
	    // setResult(RESULT_OK, result);
	    // finish();
	}
    }
}