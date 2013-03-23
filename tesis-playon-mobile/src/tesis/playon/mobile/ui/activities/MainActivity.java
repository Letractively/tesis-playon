package tesis.playon.mobile.ui.activities;

import tesis.playon.mobile.R;
import tesis.playon.mobile.R.layout;
import tesis.playon.mobile.json.model.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
}
