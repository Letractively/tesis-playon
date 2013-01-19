package tesis.playon.mobile;

import tesis.playon.mobile.json.model.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {

    private final int LOGIN_ID = 50001;

    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

	Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG).title("Hamburg"));
	Marker kiel = map.addMarker(new MarkerOptions().position(KIEL).title("Kiel").snippet("Kiel is cool")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
	// Move the camera instantly to hamburg with a zoom of 15.
	map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

	// Zoom in, animating the camera.
	map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

	// llamo a la actividad para loguearse
	Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
	startActivityForResult(loginIntent, LOGIN_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.activity_main, menu);
	return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	try {
	    switch (requestCode) {
		case LOGIN_ID: {
		    if (resultCode == Activity.RESULT_OK) {
			Bundle bundle = data.getExtras();
			Usuario usuario = (Usuario) bundle.getSerializable("json.model.usuario");
			Toast.makeText(getBaseContext(), "Logueado correctamente: " + usuario.getNombreUser(),
				Toast.LENGTH_SHORT).show();
			onSearchRequested();
		    } else if (resultCode == Activity.RESULT_CANCELED) {
			Toast.makeText(getBaseContext(), "El usuario no existe o los datos eran incorrectos!",
				Toast.LENGTH_SHORT).show();
		    }
		    break;
		}
	    }
	} catch (Exception e) {
	    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
	}
    }
}