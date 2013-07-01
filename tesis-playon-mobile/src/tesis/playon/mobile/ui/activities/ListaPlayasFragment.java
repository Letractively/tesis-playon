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
import tesis.playon.mobile.json.model.Playas;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.DialogInterface;
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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ListaPlayasFragment extends ListFragment {

    private final static String TAG = "ListaPlayasFragment";

    private static final String URL_PLAYAS = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/playas";

    private Playas playas;

    private PlayaAdapter mPlayaAdapter;

    private LayoutInflater mInflater;

    private ListView mListView;

    private String query;

    private GoogleGeoCodeResponse result;

    private PreferenceHelper mPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	Log.d(TAG, "onCreateView");

	// Bundle mBundle = getArguments();

	// mPreference = new PreferenceHelper(getActivity());

	mPreference = new PreferenceHelper(getActivity());
	query = mPreference.getQuery();
	// query = mBundle.getString(SearchManager.QUERY);

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

	mInflater = inflater;

	return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
	Log.i("FragmentList", "Item clicked: " + id);
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
	    Intent iResult = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.playas", playas);
	    iResult.putExtras(bundle);
	    if (null != query)
		new BuscarCoordenadasService().execute();
	    else {
		// playas = new Utils().buscarPlaya(playas, mPreference.getLat(), mPreference.getLng(), 10);
		playas = new Utils().buscarPlaya(playas, "-31.443579", "-64.193434", 10);
		llenarLista(playas);
	    }
	}
    }

    private void llenarLista(final Playas playas) {

	Log.d(TAG, "llenarLista");
	mListView = getListView();
	if (!playas.getPlayas().isEmpty()) {
	    mPlayaAdapter = new PlayaAdapter(mInflater.getContext(), R.layout.playa_grid_item, playas.getPlayas());
	    setListAdapter(mPlayaAdapter);

	    mListView.setAdapter(mPlayaAdapter);
	    mListView.setTextFilterEnabled(true);

	    mListView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    // Toast.makeText(getActivity().getApplicationContext(),
		    // playas.getPlayas().get(position).getNombreComercial(), Toast.LENGTH_SHORT).show();
		    String nomPlaya = playas.getPlayas().get(position).getNombreComercial();
		    Bundle playa = new Bundle();
		    playa.putString(Const.NOMBRE_PLAYA, nomPlaya);

		    Fragment fragment = new DetallePlayaFragment();
		    fragment.setArguments(playa);

		    getActivity().getActionBar().getTabAt(2).setTabListener(new MyTabsListener(fragment));
		    getActivity().getActionBar().selectTab(getActivity().getActionBar().getTabAt(2));
		}
	    });
	} else {
	    mListView.setEmptyView(getActivity().findViewById(R.id.empty_view_playas));
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
	    if (getActivity() != null) {
		llenarLista(playas);
	    }
	}
    }
}