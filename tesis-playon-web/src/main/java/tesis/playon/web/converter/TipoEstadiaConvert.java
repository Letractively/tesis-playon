/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.TipoEstadia;

/**
 * @author Pablo
 *
 */
@FacesConverter(value = "tipoEstadiaConverter")
public class TipoEstadiaConvert implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	TipoEstadia tipoEstadia = new TipoEstadia();
	tipoEstadia.setId(Integer.parseInt(toObject[0]));
	tipoEstadia.setNombre(toObject[1]);
	return tipoEstadia;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof TipoDoc) {
	    TipoEstadia tipoEstadia = (TipoEstadia) value;
	    String idTipoDoc = Integer.toString(tipoEstadia.getId());
	    String nombreTipoDoc = tipoEstadia.getNombre();
	    String toString = idTipoDoc + ":" + nombreTipoDoc;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}