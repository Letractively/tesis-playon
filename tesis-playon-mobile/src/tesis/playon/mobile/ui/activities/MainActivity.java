package tesis.playon.mobile.ui.activities;

import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Usuario;
import tesis.playon.mobile.preferences.PreferenceHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

    private static final String TAG = "Main Activity";

    private final int LOGIN_ID = 50001;

    private LocationManager locationManager;

    private String provider;

    private PreferenceHelper mPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	mPreference = new PreferenceHelper(getApplicationContext());

	// llamo a la actividad para loguearse
	Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
	startActivityForResult(loginIntent, LOGIN_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	try {
	    switch (requestCode) {
		case LOGIN_ID: {
		    saveUserLocation();
		    if (resultCode == Activity.RESULT_OK) {
			Bundle bundle = data.getExtras();
			Usuario usuario = (Usuario) bundle.getSerializable("json.model.usuario");
			PreferenceHelper mPreferences = new PreferenceHelper(getApplicationContext());
			mPreferences.updateNroUsuario(usuario.getId());
			onSearchRequested();
		    }
		    break;
		}
	    }
	} catch (Exception e) {
	    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
	}
    }

    // Initiating Menu XML file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater menuInflater = getMenuInflater();
	menuInflater.inflate(R.layout.menu, menu);
	return true;
    }

    /**
     * Event Handling for Individual menu item selected Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

	switch (item.getItemId()) {
	    case R.id.menu_login:
		Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
		startActivityForResult(loginIntent, LOGIN_ID);
		return true;

	    case R.id.menu_perfil:
		Intent perfilClienteIntent = new Intent(MainActivity.this, PerfilClienteActivity.class);
		startActivityForResult(perfilClienteIntent, LOGIN_ID);
		return true;

	    case R.id.menu_gps:
		PreferenceHelper mPreference = new PreferenceHelper(getApplicationContext());
		mPreference.updateQuery(null);
		Intent buscarPlayasIntent = new Intent(MainActivity.this, BuscarPlayasActivity.class);
		startActivityForResult(buscarPlayasIntent, LOGIN_ID);
		return true;

	    case R.id.menu_buscar:
		onSearchRequested();
		return true;

	    default:
		return super.onOptionsItemSelected(item);
	}
    }

    @Override
    public void onLocationChanged(Location location) {
	String lat = Double.toString((Double) (location.getLatitude()));
	String lng = Double.toString((Double) (location.getLongitude()));
	mPreference = new PreferenceHelper(getApplicationContext());
	mPreference.updateLatLng(lat, lng);
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void saveUserLocation() {
	// Get the location manager
	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	// Define the criteria how to select the location provider
	Criteria criteria = new Criteria();
	provider = locationManager.getBestProvider(criteria, false);
	Location location = locationManager.getLastKnownLocation(provider);

	// Initialize the location fields
	if (location != null) {
	    Log.d(TAG, "Provider " + provider + " has been selected.");
	    onLocationChanged(location);
	} else {
	    location = new Location("Hardcoded");
	    location.setLatitude(-33.503588);
	    location.setLongitude(-70.757669);
	}
	onLocationChanged(location);
    }
}