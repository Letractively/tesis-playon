package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.ColorVehiculo;

@FacesConverter(value = "barrioConverter")
public class ColorVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	ColorVehiculo color = new ColorVehiculo();
	color.setId(Integer.parseInt(toObject[0]));
	color.setNombre(toObject[1]);
	return color;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof ColorVehiculo) {
	    ColorVehiculo color = (ColorVehiculo) value;
	    String idColor = Integer.toString(color.getId());
	    String nombreBarrio = color.getNombre();
	    String toString = idColor + ":" + nombreBarrio;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}