package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.Tarifa;

/**
 * 
 * @author gmorales
 * 
 */
@FacesConverter(value = "tarifaConverter")
public class TarifaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	Tarifa tarifa = new Tarifa();
	tarifa.setId(Integer.parseInt(toObject[0]));
	tarifa.setImporte(Float.parseFloat(toObject[1]));
	return tarifa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof Tarifa) {
	    Tarifa tarifa = (Tarifa) value;
	    String idTarifa = Integer.toString(tarifa.getId());
	    String importeTarifa = Float.toString(tarifa.getImporte());
	    String toString = idTarifa + ":" + importeTarifa;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}