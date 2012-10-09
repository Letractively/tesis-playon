package tesis.playon.mobile;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener {

    // Button ok, back, exit;
    // TextView result;

    // /** Called when the activity is first created. */
    // @Override
    // public void onCreate(Bundle savedInstanceState) {
    // super.onCreate(savedInstanceState);
    // setContentView(R.layout.login);
    //
    // // Login button clicked
    // ok = (Button) findViewById(R.id.btn_login);
    // ok.setOnClickListener(this);
    //
    // result = (TextView) findViewById(R.id.lbl_result);
    //
    // }
    //
    // public void postLoginData() {
    // // Create a new HttpClient and Post Header
    // HttpClient httpclient = new DefaultHttpClient();
    //
    // /* login.php returns true if username and password is equal to saranga */
    // // http://localhost:8080/tesis-playon-restful/usuario/gmorale
    // HttpPost httppost = new HttpPost("http://www.sencide.com/blog/login.php");
    //
    // try {
    // // Add user name and password
    // EditText uname = (EditText) findViewById(R.id.txt_username);
    // String username = uname.getText().toString();
    //
    // EditText pword = (EditText) findViewById(R.id.txt_password);
    // String password = pword.getText().toString();
    //
    // List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    // nameValuePairs.add(new BasicNameValuePair("username", username));
    // nameValuePairs.add(new BasicNameValuePair("password", password));
    // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    //
    // // Execute HTTP Post Request
    // Log.w("SENCIDE", "Execute HTTP Post Request");
    // HttpResponse response = httpclient.execute(httppost);
    //
    // String str = inputStreamToString(response.getEntity().getContent()).toString();
    // Log.w("SENCIDE", str);
    //
    // if (str.toString().equalsIgnoreCase("true")) {
    // Log.w("SENCIDE", "TRUE");
    // result.setText("Login successful");
    // } else {
    // Log.w("SENCIDE", "FALSE");
    // result.setText(str);
    // }
    //
    // } catch (ClientProtocolException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // private StringBuilder inputStreamToString(InputStream is) {
    // String line = "";
    // StringBuilder total = new StringBuilder();
    // // Wrap a BufferedReader around the InputStream
    // BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    // // Read response until the end
    // try {
    // while ((line = rd.readLine()) != null) {
    // total.append(line);
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // // Return full string
    // return total;
    // }
    //
    // @Override
    // public void onClick(View view) {
    // if (view == ok) {
    // postLoginData();
    // }
    // }
    //

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.login);
	findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
	Button b = (Button) findViewById(R.id.btn_login);
	b.setClickable(false);
	new LoginService().execute();
    }

    class LoginService extends AsyncTask<Void, Void, String> {

	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	    InputStream in = entity.getContent();
	    StringBuffer out = new StringBuffer();
	    int n = 1;
	    while (n > 0) {
		byte[] b = new byte[4096];
		n = in.read(b);
		if (n > 0)
		    out.append(new String(b, 0, n));
	    }
	    return out.toString();
	}

	@Override
	protected String doInBackground(Void... params) {
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpContext localContext = new BasicHttpContext();
	    HttpGet httpGet = new HttpGet("http://localhost:8080/tesis-playon-restful/usuario/gmorales");
	    String text = null;
	    try {
		HttpResponse response = httpClient.execute(httpGet, localContext);
		HttpEntity entity = response.getEntity();
		text = getASCIIContentFromEntity(entity);
	    } catch (Exception e) {
		return e.getLocalizedMessage();
	    }
	    return text;
	}

	protected void onPostExecute(String results) {
	    if (results != null) {
		EditText et = (EditText) findViewById(R.id.btn_login);
		et.setText(results);
	    }
	    Button b = (Button) findViewById(R.id.btn_login);
	    b.setClickable(true);
	}
    }

}