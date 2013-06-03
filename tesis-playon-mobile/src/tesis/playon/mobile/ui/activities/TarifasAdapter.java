package tesis.playon.mobile.ui.activities;

import java.util.List;

import tesis.playon.mobile.R;
import tesis.playon.mobile.json.model.Tarifa;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TarifasAdapter extends ArrayAdapter<Tarifa> {

    Context context;
    int layoutResourceId;
    List<Tarifa> listaTarifas = null;

    public TarifasAdapter(Context context, int layoutResourceId, List<Tarifa> listaTarifas) {
	super(context, layoutResourceId, listaTarifas);
	this.layoutResourceId = layoutResourceId;
	this.context = context;
	this.listaTarifas = listaTarifas;
    }

    static class ViewHolder {
	TextView txtVehiculo;
	TextView txtTarifa;
	TextView txtImporte;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	View row = convertView;
	ViewHolder holder = null;

	if (row == null) {
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    row = inflater.inflate(layoutResourceId, parent, false);

	    holder = new ViewHolder();
	    holder.txtVehiculo = (TextView) row.findViewById(R.id.txt_vehiculo);
	    holder.txtTarifa = (TextView) row.findViewById(R.id.txt_tarifa);
	    holder.txtImporte = (TextView) row.findViewById(R.id.txt_importe);

	    row.setTag(holder);
	} else {
	    holder = (ViewHolder) row.getTag();
	}

	Tarifa tarifa = listaTarifas.get(position);
	holder.txtVehiculo.setText(tarifa.getCategoriaVehiculo().getNombre());
	holder.txtTarifa.setText(tarifa.getTipoEstadia().getNombre());
	holder.txtTarifa.setText(tarifa.getImporte().toString());

	return row;
    }

}