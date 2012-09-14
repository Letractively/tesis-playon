package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.MarcaVehiculo;

@FacesConverter(value = "marcaVehiculoConverter")
public class MarcaVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (!value.equals("-1")) {
	    String toObject[] = value.split(":");
	    MarcaVehiculo marca = new MarcaVehiculo();
	    marca.setId(Integer.parseInt(toObject[0]));
	    marca.setNombre(toObject[1]);
	    return marca;
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof MarcaVehiculo) {
	    MarcaVehiculo marca = (MarcaVehiculo) value;
	    String idMarca = Integer.toString(marca.getId());
	    String nombreMarca = marca.getNombre();
	    String toString = idMarca + ":" + nombreMarca;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }

}
