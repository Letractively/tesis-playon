package tesis.playon.mobile.ui.activities;

import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final int LOGIN_ID = 50001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

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
		Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
		return true;

	    case R.id.menu_perfil:
		Toast.makeText(MainActivity.this, "Perfil del cliente", Toast.LENGTH_SHORT).show();
		return true;

	    case R.id.menu_buscar:
		Toast.makeText(MainActivity.this, "Buscar", Toast.LENGTH_SHORT).show();
		return true;

	    default:
		return super.onOptionsItemSelected(item);
	}
    }
}