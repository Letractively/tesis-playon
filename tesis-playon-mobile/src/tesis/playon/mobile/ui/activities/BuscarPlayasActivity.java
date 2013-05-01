package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Playas;
import tesis.playon.mobile.utils.Utils;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

public class BuscarPlayasActivity extends ListActivity {

    private final static String LOG_TAG = "BuscarPlayasActivity";

    private static final String URL_PLAYAS = "http://10.0.2.2:8080/tesis-playon-restful/playas";

    // private static final String URL_PLAYAS = "http://192.168.5.61:8080/tesis-playon-restful/playas";

    private Playas playas;

    private ListView mListView;

    private PlayaAdapter mPlayaAdapter;

    private List<Playa> mListaPlayas = new ArrayList<Playa>();

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.playas);

	// ActionBar actionBar = getActionBar();
	// actionBar.show();

	// handleIntent(getIntent());

	// Create the list fragment and add it as our sole content.
	// if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
	// ListaPlayasFragment list = new ListaPlayasFragment();
	// getFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
	// }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	    case R.id.action_list:
		if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
		    MapaPlayasFragment list = new MapaPlayasFragment();
		    getFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
		}
		Toast.makeText(this, "Lista de playas", Toast.LENGTH_SHORT).show();
		break;
	    case R.id.action_map:
		if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
		    MapaPlayasFragment list = new MapaPlayasFragment();
		    getFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
		}
		Toast.makeText(this, "Mapa de playas", Toast.LENGTH_SHORT).show();
		break;

	    default:
		break;
	}

	return true;
    }

    public void onNewIntent(Intent intent) {
	setIntent(intent);
	handleIntent(intent);
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

    private void llenarLista(ArrayList<Playa> playas) {

	mListaPlayas = playas;
	mPlayaAdapter = new PlayaAdapter(this, R.layout.grid_item, playas);
	mListView = getListView();
	mListView.setAdapter(mPlayaAdapter);
	mListView.setTextFilterEnabled(true);

	mListView.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getApplicationContext(), mListaPlayas.get(position).getNombreComercial(),
			Toast.LENGTH_SHORT).show();
	    }
	});
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
		Log.d(LOG_TAG, "Playa: " + playa.getRazonSocial() + " Direcci�n: " + playa.getDomicilio());
	    }
	    llenarLista((ArrayList<Playa>) playas.getPlayas());
	}
    }

}