package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Comentarios;
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

public class ComentariosActivity extends ListActivity {

    private static final String TAG = "ComentariosActivity";

    private static final String URL_COMENTARIOS_PLAYA = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/comentarios/";

    private ComentariosAdapter mComentariosAdapter;

    private ListView mListView;

    private Context mContext;

    private String nomPlaya;

    private Comentarios comentarios;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lista_comentarios);
	mContext = getApplicationContext();

	PreferenceHelper mPreferences = new PreferenceHelper(mContext);
	nomPlaya = mPreferences.getNomPlaya();

	if (Utils.isOnline(mContext)) {
	    new BuscarComentariosPlayaService().execute();
	} else {
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    alertDialogBuilder.setTitle("Problema de conexión").setMessage("No está conectado a Internet")
		    .setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			    ComentariosActivity.this.finish();
			}
		    });
	    AlertDialog alertDialog = alertDialogBuilder.create();
	    alertDialog.show();
	}
    }

    private void cargarComentariosPlaya(Comentarios comentarios) {

	Log.d(TAG, "cargarComentariosPlaya");
	mListView = getListView();
	if (null != comentarios.getComentarios()) {
	    mComentariosAdapter = new ComentariosAdapter(mContext, R.layout.comentario_grid_item,
		    comentarios.getComentarios());
	    setListAdapter(mComentariosAdapter);
	    mListView.setAdapter(mComentariosAdapter);
	    mListView.setTextFilterEnabled(true);
	} else {
	    mListView.setEmptyView(findViewById(R.id.empty_view_comentarios));
	}
    }

    class BuscarComentariosPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_COMENTARIOS_PLAYA
		    + nomPlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    try {
		InputStream source = new Utils().retrieveStream(url);
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(source);
		comentarios = gson.fromJson(reader, Comentarios.class);
		return comentarios.toString();
	    } catch (Exception e) {
		e.getStackTrace();
	    }
	    return null;

	}

	protected void onPostExecute(String results) {
	    Log.d(TAG, "onPostExecute");
	    Intent result = new Intent();
	    Bundle bundle = new Bundle();
	    bundle.putSerializable("json.model.comentarios", comentarios);
	    result.putExtras(bundle);
	    cargarComentariosPlaya(comentarios);
	}
    }

}
