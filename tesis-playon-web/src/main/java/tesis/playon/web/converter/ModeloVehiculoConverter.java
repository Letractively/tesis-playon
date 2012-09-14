package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.ModeloVehiculo;

@FacesConverter(value = "modeloVehiculoConverter")
public class ModeloVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (!value.equals("-1")) {
	    String toObject[] = value.split(":");
	    ModeloVehiculo modelo = new ModeloVehiculo();
	    modelo.setId(Integer.parseInt(toObject[0]));
	    modelo.setNombre(toObject[1]);
	    return modelo;
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof ModeloVehiculo) {
	    ModeloVehiculo modelo = (ModeloVehiculo) value;
	    String idModelo = Integer.toString(modelo.getId());
	    String nombreModelo = modelo.getNombre();
	    String toString = idModelo + ":" + nombreModelo;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }

}
