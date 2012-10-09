package tesis.playon.mobile;

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

    // @Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    // getMenuInflater().inflate(R.menu.activity_main, menu);
    // return true;
    // }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	try {
	    switch (requestCode) {
		case LOGIN_ID: {
		    if (resultCode == Activity.RESULT_OK) {
			Toast.makeText(getBaseContext(), "Logueado correctamente", Toast.LENGTH_SHORT).show();
		    } else {
			Toast.makeText(getBaseContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
		    }
		    break;
		}
	    }
	} catch (Exception e) {
	    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
	}
    }
}
