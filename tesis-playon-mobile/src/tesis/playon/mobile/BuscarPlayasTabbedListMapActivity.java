package tesis.playon.mobile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.json.model.Playas;
import tesis.playon.mobile.util.Constants;
import tesis.playon.mobile.util.Utils;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.gson.Gson;

public class BuscarPlayasTabbedListMapActivity extends MapActivity implements OnTabChangeListener {

    private final static String LOG_TAG = "BuscarPlayasTabbedListMapActivity";

    private static final String LIST_TAB_TAG = "List";

    private static final String MAP_TAB_TAG = "Map";

    private static final String URL_PLAYAS = "http://" + Constants.SERVER_IP + ":8080/tesis-playon-restful/playas";

    private Playas playas;

    private TabHost mTabHost;

    private ListView mListView;

    private MapView mMapView;

    private PlayaAdapter mPlayaAdapter;

    private List<Playa> mListaPlayas = new ArrayList<Playa>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lista_playas);

	mTabHost = (TabHost) findViewById(android.R.id.tabhost);

	// setup must be called if you are not inflating the mTabHost from XML
	mTabHost.setup();
	mTabHost.setOnTabChangedListener(this);

	// setup list view
	mListView = (ListView) findViewById(R.id.list);
	mListView.setEmptyView((TextView) findViewById(R.id.empty));

	// add an onclicklistener to see point on the map
	mListView.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
		GeoPoint geoPoint = (GeoPoint) mListView.getAdapter().getItem(position);
		if (geoPoint != null) {
		    // have map view moved to this point
		    setMapZoomPoint(geoPoint, 12);
		    // programmatically switch tabs to the map view
		    mTabHost.setCurrentTab(1);
		}
	    }
	});

	// setup map view
	mMapView = (MapView) findViewById(R.id.mapview);
	mMapView.setBuiltInZoomControls(true);
	mMapView.postInvalidate();

	// add views to tab host
	mTabHost.addTab(mTabHost.newTabSpec(LIST_TAB_TAG).setIndicator("List").setContent(new TabContentFactory() {
	    public View createTabContent(String arg0) {
		return mListView;
	    }
	}));
	mTabHost.addTab(mTabHost.newTabSpec(MAP_TAB_TAG).setIndicator("Map").setContent(new TabContentFactory() {
	    public View createTabContent(String arg0) {
		return mMapView;
	    }
	}));

	// HACK to get the list view to show up first,
	// otherwise the mapview would be bleeding through and visible
	mTabHost.setCurrentTab(1);
	mTabHost.setCurrentTab(0);

	handleIntent(getIntent());
    }

    @Override
    public boolean onSearchRequested() {
	return super.onSearchRequested();
    }

    @Override
    public void onNewIntent(Intent intent) {
	super.onNewIntent(intent);
	setIntent(intent);
	handleIntent(intent);
    }

    @Override
    public void onTabChanged(String tabName) {
	if (tabName.equals(MAP_TAB_TAG)) {
	    // do something on the map
	} else if (tabName.equals(LIST_TAB_TAG)) {
	    // do something on the list
	}
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
	// mListView = getListView();
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

    // ========================================================================================================
    // ========================================================================================================
    // ========================================================================================================
    // ========================================================================================================
    // ========================================================================================================

    /**
     * Instructs the map view to navigate to the point and zoom level specified.
     * 
     * @param geoPoint
     * @param zoomLevel
     */
    private void setMapZoomPoint(GeoPoint geoPoint, int zoomLevel) {
	mMapView.getController().setCenter(geoPoint);
	mMapView.getController().setZoom(zoomLevel);
	mMapView.postInvalidate();
    }

    /**
     * From MapActivity, we ignore it for this demo
     */
    protected boolean isRouteDisplayed() {
	return false;
    }

}