package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Playas;
import tesis.playon.mobile.utils.Utils;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

public class ListaPlayasFragment extends ListFragment {

    private final static String TAG = "ListaPlayasFragment";

    private static final String URL_PLAYAS = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/playas";

    private Playas playas;

    private ArrayList<Playa> mListaPlayas = new ArrayList<Playa>();

    private PlayaAdapter mPlayaAdapter;

    private LayoutInflater mInflater;

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	Log.d(TAG, "onCreateView");

	Bundle mBundle = getArguments();

	String query = mBundle.getString(SearchManager.QUERY);
	handleQuery(query);
	mInflater = inflater;

	return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
	Log.i("FragmentList", "Item clicked: " + id);
    }

    private void handleQuery(String query) {

	Log.d(TAG, "handleQuery");
	doSearch(query);
	new BuscarPlayaService().execute();
    }

    private void doSearch(String queryStr) {
	// get a Cursor, prepare the ListAdapter and set it
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
	    llenarLista((ArrayList<Playa>) playas.getPlayas());
	}
    }

    private void llenarLista(ArrayList<Playa> playas) {

	Log.d(TAG, "llenarLista");
	mListaPlayas = playas;
	mPlayaAdapter = new PlayaAdapter(mInflater.getContext(), R.layout.grid_item, mListaPlayas);
	setListAdapter(mPlayaAdapter);
	mListView = getListView();
	mListView.setAdapter(mPlayaAdapter);
	mListView.setTextFilterEnabled(true);

	mListView.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getActivity().getApplicationContext(), mListaPlayas.get(position).getNombreComercial(),
			Toast.LENGTH_SHORT).show();
		String nomPlaya = mListaPlayas.get(position).getNombreComercial();
		Bundle playa = new Bundle();
		playa.putString(Const.NOMBRE_PLAYA, nomPlaya);

		Fragment fragment = new DetallePlayaFragment();
		fragment.setArguments(playa);

		getActivity().getActionBar().getTabAt(2).setTabListener(new MyTabsListener(fragment));
		getActivity().getActionBar().selectTab(getActivity().getActionBar().getTabAt(2));
	    }
	});
    }
}