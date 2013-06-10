package tesis.playon.mobile.ui.activities;

import java.util.List;

import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Promocion;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PromocionesAdapter extends ArrayAdapter<Promocion> {

    Context context;
    int layoutResourceId;
    List<Promocion> listaPromociones = null;

    public PromocionesAdapter(Context context, int layoutResourceId, List<Promocion> listaPromociones) {
	super(context, layoutResourceId, listaPromociones);
	this.layoutResourceId = layoutResourceId;
	this.context = context;
	this.listaPromociones = listaPromociones;
    }

    static class ViewHolder {
	TextView txtDescripcion;
	TextView txtVehiculo;
	TextView txtPrecio;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	View row = convertView;
	ViewHolder holder = null;

	if (row == null) {
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    row = inflater.inflate(layoutResourceId, parent, false);

	    holder = new ViewHolder();
	    holder.txtDescripcion = (TextView) row.findViewById(R.id.txt_descripcion);
	    holder.txtVehiculo = (TextView) row.findViewById(R.id.txt_vehiculo);
	    holder.txtPrecio = (TextView) row.findViewById(R.id.txt_precio);

	    row.setTag(holder);
	} else {
	    holder = (ViewHolder) row.getTag();
	}

	Promocion promocion = listaPromociones.get(position);
	holder.txtDescripcion.setText(promocion.getDescripcion());
	holder.txtVehiculo.setText(promocion.getTarifa().getCategoriaVehiculo().getNombre());
	holder.txtPrecio.setText("$" + promocion.getTarifa().getImporte().toString());

	return row;
    }

}