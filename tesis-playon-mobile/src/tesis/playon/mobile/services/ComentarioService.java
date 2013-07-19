package tesis.playon.mobile.services;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import tesis.playon.mobile.Const;
import android.app.IntentService;
import android.content.Intent;

public class ComentarioService extends IntentService {

    private static final String JSON_COMENTARIO_ENTITY = "comentario-entity";

    private static final String JSON_COMENTARIO = "comentario";

    private static final String JSON_PLAYA = "playa";

    private static final String JSON_USUARIO = "usuario";

    private static final String JSON_SUCCESS = "success";

    public static final String ENVIAR_COMENTARIO = "enviar comentario";

    public static final String COMENTARIO = "comentario";

    public static final String PLAYA = "playa";

    public static final String USUARIO = "usuario";

    public static final String UPDATE_COMENTARIOS = "tesis.playon.mobile.save.comentario";

    public ComentarioService() {
	super("ComentarioService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
	String action = intent.getAction();
	if (action != null && action.equals(ENVIAR_COMENTARIO)) {
	    String comentario = intent.getStringExtra(COMENTARIO);
	    Integer playa = intent.getIntExtra(PLAYA, -1);
	    Integer usuario = intent.getIntExtra(USUARIO, -1);

	    boolean result = enviarComentario(comentario, playa, usuario);
	    if (result) {
		notifyComentarioEnviado(comentario, playa, usuario);
	    }
	}
    }

    private void notifyComentarioEnviado(String comentario, Integer playa, Integer usuario) {
	Intent intent = new Intent();
	intent.setAction(UPDATE_COMENTARIOS);
	intent.putExtra(COMENTARIO, comentario);
	intent.putExtra(PLAYA, usuario);
	intent.putExtra(USUARIO, playa);
	sendBroadcast(intent);
    }

    private boolean enviarComentario(String comentario, Integer playa, Integer usuario) {
	boolean result = false;

	String url = "http://" + Const.SERVER_IP + ":8080/tesis-playon-restful/comentarionuevo";

	DefaultHttpClient client = new DefaultHttpClient();

	HttpPost postRequest = new HttpPost(url);
	String jsonBody = getJSONComentarioBody(comentario, playa, usuario);

	try {
	    StringEntity entity = new StringEntity(jsonBody);
	    postRequest.setHeader("Content-Type", "application/json");
	    postRequest.setEntity(entity);

	    HttpResponse response = client.execute(postRequest);

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    response.getEntity().writeTo(out);
	    out.close();
	    byte[] array = out.toByteArray();
	    String TempResponse = new String(array);
	    JSONObject jsonResponse = new JSONObject(TempResponse);
	    result = jsonResponse.getBoolean(JSON_SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    private String getJSONComentarioBody(String comentario, Integer playa, Integer usuario) {

	JSONObject body = new JSONObject();

	try {
	    JSONObject comentarioJSON = new JSONObject();
	    comentarioJSON.put(JSON_COMENTARIO, comentario);
	    comentarioJSON.put(JSON_PLAYA, playa);
	    comentarioJSON.put(JSON_USUARIO, usuario);
	    body.put(JSON_COMENTARIO_ENTITY, comentarioJSON);
	} catch (JSONException e) {
	    e.printStackTrace();
	}
	return body.toString();
    }
}