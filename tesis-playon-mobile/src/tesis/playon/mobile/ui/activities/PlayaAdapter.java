package tesis.playon.mobile.ui.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import tesis.playon.mobile.Const;
import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.PerfilPlaya;
import tesis.playon.mobile.json.model.Playa;
import tesis.playon.mobile.utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class PlayaAdapter extends ArrayAdapter<Playa> {

    private static final String URL_PERFIL_PLAYA = "http://" + Const.SERVER_IP
	    + ":8080/tesis-playon-restful/perfilplaya/";

    Context context;
    int layoutResourceId;
    List<Playa> listaPlayas = null;
    Playa playa;
    PerfilPlaya perfilPlaya;

    public PlayaAdapter(Context context, int layoutResourceId, List<Playa> listaPlayas) {
	super(context, layoutResourceId, listaPlayas);
	this.layoutResourceId = layoutResourceId;
	this.context = context;
	this.listaPlayas = listaPlayas;
    }

    static class ViewHolder {
	ImageView imagen;
	TextView txtPlayaNombre;
	TextView txtPlayaDireccion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

	View row = convertView;
	ViewHolder holder = null;

	if (row == null) {
	    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
	    row = inflater.inflate(layoutResourceId, parent, false);

	    holder = new ViewHolder();
	    holder.imagen = (ImageView) row.findViewById(R.id.grid_playa_image);
	    holder.txtPlayaNombre = (TextView) row.findViewById(R.id.nombrePlaya);
	    holder.txtPlayaDireccion = (TextView) row.findViewById(R.id.direccionPlaya);

	    row.setTag(holder);
	} else {
	    holder = (ViewHolder) row.getTag();
	}

	playa = listaPlayas.get(position);
	holder.txtPlayaNombre.setText(playa.getNombreComercial());
	holder.txtPlayaDireccion.setText(playa.getDomicilio());

	BuscarPerfilPlayaService mFotoPerfilPlaya = new BuscarPerfilPlayaService();
	mFotoPerfilPlaya.execute();

	while (!mFotoPerfilPlaya.isCancelled()) {

	}

	if (null != perfilPlaya.getFotoPerfil()) {

	    byte[] decodedString = Base64.decode(perfilPlaya.getFotoPerfil(), Base64.DEFAULT);
	    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
	    if (bitmap != null) {
		Drawable image = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100,
			true));
		holder.imagen.setImageDrawable(image);
	    }
	}

	return row;
    }

    class BuscarPerfilPlayaService extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
	    String url = URL_PERFIL_PLAYA
		    + playa.getNombreComercial().replace(" ", "%20").replace("á", "a").replace("é", "e")
			    .replace("í", "i").replace("ó", "o").replace("ú", "u").replace("ñ", "n");
	    InputStream source = new Utils().retrieveStream(url);
	    Gson gson = new Gson();
	    Reader reader = new InputStreamReader(source);
	    perfilPlaya = gson.fromJson(reader, PerfilPlaya.class);
	    cancel(true);
	    return perfilPlaya.toString();
	}

    }

}