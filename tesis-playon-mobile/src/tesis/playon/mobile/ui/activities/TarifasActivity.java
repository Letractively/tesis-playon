package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Tarifas;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

public class TarifasActivity extends ListActivity {

    private static final String TAG = "TarifasActi vity";

    private static final String URL_TARIFAS_PLAYA = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/tarifas/";

    private TarifasAdapter mTarifaAdapter;

    private ListView mListView;

    private Context mContext;

    private String nomPlaya;

    private Tarifas tarifas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lista_tarifas);
	mContext = getApplicationContext();

	PreferenceHelper mPreferences = new PreferenceHelper(mContext);
	nomPlaya = mPreferences.getNomPlaya();

	if (Utils.isOnline(mContext)) {
	    new BuscarTarifasVigentesPlayaService().execute();
	} else {
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    alertDialogBuilder.setTitle("Problema de conexión").setMessage("No está conectado a Internet")
		    .setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			    TarifasActivity.this.finish();
			}
		    });
	    AlertDialog alertDialog = alertDialogBuilder.create();
	    alertDialog.show();
	}
    }

    private void cargarTarifasPlaya(Tarifas tarifas) {

	Log.d(TAG, "cargarTarifasPlaya");
	mListView = getListView();
	if (null != tarifas.getTarifas()) {
	    mTarifaAdapter = new TarifasAdapter(mContext, R.layout.tarifa_grid_item, tarifas.getTarifas());
	    setListAdapter(mTarifaAdapter);
	    mListView.setAdapter(mTarifaAdapter);
	    mListView.setTextFilterEnabled(true);
	} else {
	    mListView.setEmptyView(findViewById(R.id.empty_view_tarifas));
	}

    }

    class BuscarTarifasVigentesPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_TARIFAS_PLAYA
		    + nomPlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    tarifas = gson.fromJson(reader, Tarifas.class);
	    return tarifas.toString();
	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.tarifas", tarifas);
	    result.putExtras(bundle);
	    cargarTarifasPlaya(tarifas);
	}
    }

}
