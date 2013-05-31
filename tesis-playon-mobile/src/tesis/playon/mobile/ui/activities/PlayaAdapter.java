package tesis.playon.mobile.ui.activities;

import java.util.List;

import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Playa;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlayaAdapter extends ArrayAdapter<Playa> {

    Context context;
    int layoutResourceId;
    List<Playa> listaPlayas = null;

    public PlayaAdapter(Context context, int layoutResourceId, List<Playa> listaPlayas) {
	super(context, layoutResourceId, listaPlayas);
	this.layoutResourceId = layoutResourceId;
	this.context = context;
	this.listaPlayas = listaPlayas;
    }

    static class ViewHolder {
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
	    holder.txtPlayaNombre = (TextView) row.findViewById(R.id.nombrePlaya);
	    holder.txtPlayaDireccion = (TextView) row.findViewById(R.id.direccionPlaya);

	    row.setTag(holder);
	} else {
	    holder = (ViewHolder) row.getTag();
	}

	Playa playa = listaPlayas.get(position);
	holder.txtPlayaNombre.setText(playa.getNombreComercial());
	holder.txtPlayaDireccion.setText(playa.getDomicilio());

	return row;
    }

}