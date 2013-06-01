package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Promociones;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

public class PromocionesActivity extends Activity {

    private static final String TAG = "PromocionesActivity";

    private static final String URL_PROMOCIONES_PLAYA = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/promociones/";

    private Context mContext;

    private String nomPlaya;

    private Promociones promociones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.playa_promociones);
	mContext = getApplicationContext();

	PreferenceHelper mPreferences = new PreferenceHelper(mContext);
	nomPlaya = mPreferences.getNomPlaya();

	new BuscarPromocionesVigentesPlayaService().execute();
    }

    private void cargarPromocionesPlaya(Promociones promociones) {

	Log.d(TAG, "cargarPromocionesPlaya");

    }

    class BuscarPromocionesVigentesPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_PROMOCIONES_PLAYA
		    + nomPlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    promociones = gson.fromJson(reader, Promociones.class);
	    return promociones.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.promociones", promociones);
	    result.putExtras(bundle);
	    cargarPromocionesPlaya(promociones);
	}
    }

}
