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
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

public class ListaPlayasFragment extends ListFragment {

    private final static String LOG_TAG = "ListaPlayasFragment";

    String[] countries = new String[] { "India", "Pakistan", "Sri Lanka", "China", "Bangladesh", "Nepal",
	    "Afghanistan", "North Korea", "South Korea", "Japan" };

    private static final String URL_PLAYAS = "http://10.0.2.2:8080/tesis-playon-restful/playas";

    // private static final String URL_PLAYAS = "http://192.168.5.61:8080/tesis-playon-restful/playas";

    private Playas playas;

    private ArrayList<Playa> mListaPlayas = new ArrayList<Playa>();

    private PlayaAdapter mPlayaAdapter;

    private LayoutInflater mInflater;

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);

	String query = savedInstanceState.getString(SearchManager.QUERY);
	handleQuery(query);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
	// android.R.layout.simple_list_item_1, countries);

	PlayaAdapter adapter = new PlayaAdapter(inflater.getContext(), R.layout.grid_item, mListaPlayas);

	// TODO: filling list
	// mInflater = inflater;
	// String query = savedInstanceState.getString(SearchManager.QUERY);
	// handleQuery(query);

	setListAdapter(adapter);

	return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
	Log.i("FragmentList", "Item clicked: " + id);
    }

    private void handleQuery(String query) {

	doSearch(query);
	new BuscarPlayaService().execute();

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
	    llenarLista((ArrayList<Playa>) playas.getPlayas());
	}
    }

    private void llenarLista(ArrayList<Playa> playas) {

	mListaPlayas = playas;
	// mPlayaAdapter = new PlayaAdapter(mInflater.getContext(), R.layout.grid_item, playas);
	// mListView = getListView();
	// mListView.setAdapter(mPlayaAdapter);
	// mListView.setTextFilterEnabled(true);

	// mListView.setOnItemClickListener(new OnItemClickListener() {
	// public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// Toast.makeText(getApplicationContext(), mListaPlayas.get(position).getNombreComercial(),
	// Toast.LENGTH_SHORT).show();
	// }
	// });
    }

}