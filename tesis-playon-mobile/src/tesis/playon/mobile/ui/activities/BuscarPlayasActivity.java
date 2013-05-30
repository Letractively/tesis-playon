package tesis.playon.mobile.ui.activities;

import tesis.playon.mobile.R;
import tesis.playon.mobile.preferences.PreferenceHelper;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BuscarPlayasActivity extends Activity {

    private final static String TAG = "BuscarPlayasActivity";

    public static Context appContext;

    public void onCreate(Bundle savedInstanceState) {

	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.playas);

	appContext = getApplicationContext();

	String query = handleIntent(getIntent());
	PreferenceHelper mPreference = new PreferenceHelper(appContext);
	mPreference.updateQuery(query);
	Bundle args = new Bundle();
	args.putString(SearchManager.QUERY, query);

	Fragment listaFragment = new ListaPlayasFragment();
	listaFragment.setArguments(args);

	Fragment mapaFragment = new MapaPlayasFragment();

	Fragment playaFragment = new DetallePlayaFragment();

	// ActionBar
	final ActionBar actionbar = getActionBar();
	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	ActionBar.Tab listaTab = actionbar.newTab().setText("Lista");
	ActionBar.Tab mapaTab = actionbar.newTab().setText("Mapa");
	ActionBar.Tab playaTab = actionbar.newTab().setText("Playa");

	listaTab.setTabListener(new MyTabsListener(listaFragment));
	mapaTab.setTabListener(new MyTabsListener(mapaFragment));
	playaTab.setTabListener(new MyTabsListener(playaFragment));

	actionbar.addTab(listaTab);
	actionbar.addTab(mapaTab);
	actionbar.addTab(playaTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	Log.d(TAG, "onCreateOptionsMenu");
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	Log.d(TAG, "onOptionsItemSelected");
	if (item.getItemId() == R.id.action_list) {
	    Toast.makeText(this, "Lista de playas", Toast.LENGTH_SHORT).show();
	    return true;
	} else if (item.getItemId() == R.id.action_map) {
	    Toast.makeText(this, "Mapa de playas", Toast.LENGTH_SHORT).show();
	    return true;
	}
	return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
	Log.d(TAG, "onSaveInstanceState");
	super.onSaveInstanceState(outState);
	outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }

    private String handleIntent(Intent intent) {
	Log.d(TAG, "handleIntent");
	if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	    String query = intent.getStringExtra(SearchManager.QUERY);
	    Log.d(TAG, "Direccion ingresada: " + query);
	    return query;
	}
	return null;
    }

}

class MyTabsListener implements ActionBar.TabListener {

    public Fragment fragment;

    public MyTabsListener(Fragment fragment) {
	this.fragment = fragment;
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	ft.replace(R.id.fragment_container, fragment);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

}