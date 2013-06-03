package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Comentarios;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.utils.Utils;
import android.app.ListActivity;
import android.content.Context;
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

	new BuscarComentariosPlayaService().execute();
    }

    private void cargarComentariosPlaya(Comentarios comentarios) {

	Log.d(TAG, "cargarComentariosPlaya");
	mComentariosAdapter = new ComentariosAdapter(mContext, R.layout.comentario_grid_item,
		comentarios.getComentarios());
	setListAdapter(mComentariosAdapter);
	mListView = getListView();
	mListView.setAdapter(mComentariosAdapter);
	mListView.setTextFilterEnabled(true);

    }

    class BuscarComentariosPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    Log.d(TAG, "doInBackground");
	    String url = URL_COMENTARIOS_PLAYA
		    + nomPlaya.replace(" ", "%20").replace("á", "a").replace("é", "e").replace("í", "i")
			    .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    comentarios = gson.fromJson(reader, Comentarios.class);
	    return comentarios.toString();
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
