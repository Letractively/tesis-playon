package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.PlayonApp;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Comentarios;
import tesis.playon.mobile.preferences.PreferenceHelper;
import tesis.playon.mobile.services.ComentarioService;
import tesis.playon.mobile.utils.Utils;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.gson.Gson;

public class ComentariosActivity extends ListActivity {

    private static final String TAG = "ComentariosActivity";

    private static final String URL_COMENTARIOS_PLAYA = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/comentarios/";

    private ComentariosAdapter mComentariosAdapter;

    private ListView mListView;

    private Context mContext;

    private Button mButton;

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

	mButton = (Button) findViewById(R.id.comment_button);
	mButton.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(
			LAYOUT_INFLATER_SERVICE);
		final View popupView = layoutInflater.inflate(R.layout.comentario_popup, null);
		final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT, true);

		final EditText comentarioUsuario = (EditText) popupView.findViewById(R.id.txt_comentario_popup);
		comentarioUsuario.addTextChangedListener(new TextWatcher() {

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
			PreferenceHelper mPreferences = new PreferenceHelper(mContext);
			mPreferences.updateComentario(comentarioUsuario.getText().toString());
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		    }

		    @Override
		    public void afterTextChanged(Editable s) {

		    }
		});

		Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
		btnDismiss.setOnClickListener(new Button.OnClickListener() {

		    @Override
		    public void onClick(View v) {
			enviarComentarioPlaya();
			popupWindow.dismiss();
		    }

		});

		popupWindow.showAtLocation(mButton, Gravity.CENTER, 0, 0);
	    }
	});

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

    @Override
    protected void onResume() {
	super.onResume();
	IntentFilter connectionIntent = new IntentFilter();
	connectionIntent.addAction(ComentarioService.UPDATE_COMENTARIOS);
	registerReceiver(comentariosReceiver, connectionIntent);
    }

    @Override
    protected void onPause() {
	super.onPause();
	unregisterReceiver(comentariosReceiver);
    }

    private BroadcastReceiver comentariosReceiver = new BroadcastReceiver() {

	@Override
	public void onReceive(Context context, Intent intent) {
	    String action = intent.getAction();
	    if (action != null && action.equals(ComentarioService.UPDATE_COMENTARIOS)) {
		if (Utils.isOnline(mContext)) {
		    new BuscarComentariosPlayaService().execute();
		} else {
		    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ComentariosActivity.this);
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
	}
    };

    public void addItems(View v) {
	mComentariosAdapter.notifyDataSetChanged();
    }

    public void enviarComentarioPlaya() {

	PreferenceHelper mPreferences = new PreferenceHelper(mContext);

	Intent intent = new Intent(PlayonApp.getInstance(), ComentarioService.class);

	intent.setAction(ComentarioService.ENVIAR_COMENTARIO);
	intent.putExtra(ComentarioService.COMENTARIO, mPreferences.getComentario());
	intent.putExtra(ComentarioService.PLAYA, mPreferences.getIdPlaya());
	intent.putExtra(ComentarioService.USUARIO, mPreferences.getIdUsuario());

	PlayonApp.getInstance().startService(intent);
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
