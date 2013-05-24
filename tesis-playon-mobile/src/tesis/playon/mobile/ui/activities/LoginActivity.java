package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Usuario;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class LoginActivity extends Activity implements OnClickListener {

    private static final String TAG = "LoginActivity";

    private static final String URL_USUARIO = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/usuario/";

    private Context mContext;

    private TextView txtUser;

    private TextView txtPass;

    private CheckBox chkBox;

    private String username;

    private String password;

    private Usuario usuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.login);
	mContext = getApplicationContext();
	findViewById(R.id.btn_login).setOnClickListener(this);
	txtUser = (TextView) findViewById(R.id.txt_username);
	txtPass = (TextView) findViewById(R.id.txt_password);
	chkBox = (CheckBox) findViewById(R.id.chk_recordar);
    }

    @Override
    public void onClick(View arg0) {
	Log.d(TAG, "onClick");
	username = txtUser.getText().toString().trim();
	password = txtPass.getText().toString().trim();
	if (!username.equals("") && !password.equals("")) {
	    if (chkBox.isChecked()) {
		PreferenceHelper mPreferences = new PreferenceHelper(mContext);
		mPreferences.updateUsuario(username, password);
	    } else {
		PreferenceHelper mPreferences = new PreferenceHelper(mContext);
		mPreferences.updateUsuario(null, null);
	    }
	    Button b = (Button) findViewById(R.id.btn_login);
	    b.setClickable(false);
	    new LoginService().execute();
	} else {
	    Toast.makeText(getApplicationContext(), "Ingrese el nombre de usuario y la contrase�a", Toast.LENGTH_SHORT)
		    .show();
	}
    }

    class LoginService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    String url = URL_USUARIO + username;
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    usuario = gson.fromJson(reader, Usuario.class);
	    return usuario.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    if (username.equals(usuario.getNombreUser()) && password.equals(usuario.getPassword())
		    && usuario.getEnable()) {
		Intent result = new Intent();
		Bundle bundle = new Bundle();
		bundle.putSerializable("json.model.usuario", usuario);
		result.putExtras(bundle);
		setResult(RESULT_OK, result);
		finish();
	    } else {
		Intent result = new Intent();
		setResult(RESULT_CANCELED, result);
		finish();
	    }
	    Button b = (Button) findViewById(R.id.btn_login);
	    b.setClickable(true);
	}
    }
}