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
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
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
	// setContentView(R.layout.lista_playas);
	handleIntent(getIntent());

	FragmentManager fm = getFragmentManager();

	// Create the list fragment and add it as our sole content.
	if (fm.findFragmentById(android.R.id.content) == null) {
	    BuscarPlayasFragment list = new BuscarPlayasFragment();
	    fm.beginTransaction().add(android.R.id.content, list).commit();
	}
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

    public static class BuscarPlayasFragment extends ListFragment {

	private OnItemSelectedListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fragment_lista_playas, container, false);
	    return view;
	}

	public interface OnItemSelectedListener {
	    public void onRssItemSelected(String link);
	}

	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);
	    if (activity instanceof OnItemSelectedListener) {
		listener = (OnItemSelectedListener) activity;
	    } else {
		throw new ClassCastException(activity.toString()
			+ " must implemenet MyListFragment.OnItemSelectedListener");
	    }
	}

	@Override
	public void onDetach() {
	    super.onDetach();
	    listener = null;
	}

	// May also be triggered from the Activity
	public void updateDetail() {
	    // Create a string, just for testing
	    String newTime = String.valueOf(System.currentTimeMillis());

	    // Inform the Activity about the change based
	    // interface defintion
	    listener.onRssItemSelected(newTime);
	}

	// // This is the Adapter being used to display the list's data.
	// SimpleCursorAdapter mAdapter;
	//
	// // If non-null, this is the current filter the user has provided.
	// String mCurFilter;
	//
	// @Override
	// public void onActivityCreated(Bundle savedInstanceState) {
	// super.onActivityCreated(savedInstanceState);
	//
	// // Give some text to display if there is no data. In a real
	// // application this would come from a resource.
	// setEmptyText("No phone numbers");
	//
	// // We have a menu item to show in action bar.
	// setHasOptionsMenu(true);
	//
	// // Create an empty adapter we will use to display the loaded data.
	// mAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_2, null, new String[] {
	// Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS }, new int[] { android.R.id.text1,
	// android.R.id.text2 }, 0);
	// setListAdapter(mAdapter);
	//
	// // Start out with a progress indicator.
	// setListShown(false);
	//
	// // Prepare the loader. Either re-connect with an existing one,
	// // or start a new one.
	// getLoaderManager().initLoader(0, null, this);
	// }
	//
	// @Override
	// public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// // Place an action bar item for searching.
	// MenuItem item = menu.add("Search");
	// item.setIcon(android.R.drawable.ic_menu_search);
	// item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	// SearchView sv = new SearchView(getActivity());
	// sv.setOnQueryTextListener(this);
	// item.setActionView(sv);
	// }
	//
	// public boolean onQueryTextChange(String newText) {
	// // Called when the action bar search text has changed. Update
	// // the search filter, and restart the loader to do a new query
	// // with this filter.
	// mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
	// getLoaderManager().restartLoader(0, null, this);
	// return true;
	// }
	//
	// @Override
	// public boolean onQueryTextSubmit(String query) {
	// // Don't care about this.
	// return true;
	// }
	//
	// @Override
	// public void onListItemClick(ListView l, View v, int position, long id) {
	// // Insert desired behavior here.
	// Log.i("FragmentComplexList", "Item clicked: " + id);
	// }
	//
	// // These are the Contacts rows that we will retrieve.
	// static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] { Contacts._ID, Contacts.DISPLAY_NAME,
	// Contacts.CONTACT_STATUS, Contacts.CONTACT_PRESENCE, Contacts.PHOTO_ID, Contacts.LOOKUP_KEY, };
	//
	// public Loader<Cursor> onCreateLoader(int id, Bundle args) {
	// // This is called when a new Loader needs to be created. This
	// // sample only has one Loader, so we don't care about the ID.
	// // First, pick the base URI to use depending on whether we are
	// // currently filtering.
	// Uri baseUri;
	// if (mCurFilter != null) {
	// baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI, Uri.encode(mCurFilter));
	// } else {
	// baseUri = Contacts.CONTENT_URI;
	// }
	//
	// // Now create and return a CursorLoader that will take care of
	// // creating a Cursor for the data being displayed.
	// String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND (" + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
	// + Contacts.DISPLAY_NAME + " != '' ))";
	// return new CursorLoader(getActivity(), baseUri, CONTACTS_SUMMARY_PROJECTION, select, null,
	// Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
	// }
	//
	// public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
	// // Swap the new cursor in. (The framework will take care of closing the
	// // old cursor once we return.)
	// mAdapter.swapCursor(data);
	//
	// // The list should now be shown.
	// if (isResumed()) {
	// setListShown(true);
	// } else {
	// setListShownNoAnimation(true);
	// }
	// }
	//
	// public void onLoaderReset(Loader<Cursor> loader) {
	// // This is called when the last Cursor provided to onLoadFinished()
	// // above is about to be closed. We need to make sure we are no
	// // longer using it.
	// mAdapter.swapCursor(null);
	// }
    }

}