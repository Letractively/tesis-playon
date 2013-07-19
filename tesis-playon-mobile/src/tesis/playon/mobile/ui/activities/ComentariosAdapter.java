package tesis.playon.mobile.ui.activities;

import java.util.List;

import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Comentario;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ComentariosAdapter extends ArrayAdapter<Comentario> {

    Context context;
    int layoutResourceId;
    List<Comentario> listaComentarios = null;

    public ComentariosAdapter(Context context, int layoutResourceId, List<Comentario> listaComentarios) {
	super(context, layoutResourceId, listaComentarios);
	this.layoutResourceId = layoutResourceId;
	this.context = context;
	this.listaComentarios = listaComentarios;
    }

    static class ViewHolder {
	ImageView imagen;
	TextView txtComentario;
	TextView txtUsuario;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	View row = convertView;
	ViewHolder holder = null;

	if (row == null) {
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    row = inflater.inflate(layoutResourceId, parent, false);

	    holder = new ViewHolder();
	    holder.imagen = (ImageView) row.findViewById(R.id.grid_usuario_image);
	    holder.txtComentario = (TextView) row.findViewById(R.id.txt_comentario);
	    holder.txtUsuario = (TextView) row.findViewById(R.id.txt_usuario);

	    row.setTag(holder);
	} else {
	    holder = (ViewHolder) row.getTag();
	}

	Comentario comentario = listaComentarios.get(position);
	holder.txtComentario.setText(comentario.getComentario());
	holder.txtUsuario.setText(comentario.getUsuario().getNombreUser());

	if (null != comentario.getUsuario().getFoto() && null != comentario.getUsuario().getFoto().getFotoUsuario()) {

	    byte[] decodedString = Base64.decode(comentario.getUsuario().getFoto().getFotoUsuario(), Base64.DEFAULT);
	    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
	    if (bitmap != null) {
		Drawable image = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100,
			true));
		holder.imagen.setImageDrawable(image);
	    }
	}

	return row;
    }

}