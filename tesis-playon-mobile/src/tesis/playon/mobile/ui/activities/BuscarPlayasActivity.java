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
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
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

    public static Context appContext;

    private Playas playas;

    private ListView mListView;

    private PlayaAdapter mPlayaAdapter;

    private List<Playa> mListaPlayas = new ArrayList<Playa>();

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.playas);
	appContext = getApplicationContext();

	// handleIntent(getIntent());

	// ActionBar
	ActionBar actionbar = getActionBar();
	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	ActionBar.Tab listaTab = actionbar.newTab().setText("Lista");
	ActionBar.Tab mapaTab = actionbar.newTab().setText("Mapa");

	Fragment listaFragment = new ListaPlayasFragment();
	Fragment mapaFragment = new MapaPlayasFragment();

	listaTab.setTabListener(new MyTabsListener(listaFragment));
	mapaTab.setTabListener(new MyTabsListener(mapaFragment));

	actionbar.addTab(listaTab);
	actionbar.addTab(mapaTab);

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
		Toast.makeText(this, "Lista de playas", Toast.LENGTH_SHORT).show();
		return true;
	    case R.id.action_map:
		Toast.makeText(this, "Mapa de playas", Toast.LENGTH_SHORT).show();
		return true;
	}
	return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }

    // TODO: do not delete this code for future implementation

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
		Log.d(LOG_TAG, "Playa: " + playa.getRazonSocial() + " Dirección: " + playa.getDomicilio());
	    }
	    llenarLista((ArrayList<Playa>) playas.getPlayas());
	}
    }

}

class MyTabsListener implements ActionBar.TabListener {

    public Fragment fragment;

    public MyTabsListener(Fragment fragment) {
	this.fragment = fragment;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	Toast.makeText(BuscarPlayasActivity.appContext, "Reselected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	ft.replace(R.id.listFragment, fragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	ft.remove(fragment);
    }

}