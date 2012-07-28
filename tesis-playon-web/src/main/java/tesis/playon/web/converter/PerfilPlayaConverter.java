/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.PerfilPlaya;
/**
 * @author pablo
 *
 */
@FacesConverter(value = "provinciaConverter")
public class PerfilPlayaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	PerfilPlaya perfil = new PerfilPlaya();
	perfil.setId(Integer.parseInt(toObject[0]));
	perfil.setNombre(toObject[1]);
	return perfil;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof PerfilPlaya) {
	    PerfilPlaya perfil = (PerfilPlaya) value;
	    String idPerfil = Integer.toString(perfil.getId());
	    String nombre = perfil.getNombre();
	    String toString = idPerfil + ":" + nombre + ":" + nombre;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }


    
}
