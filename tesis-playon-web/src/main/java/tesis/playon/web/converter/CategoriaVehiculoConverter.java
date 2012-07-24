/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.TipoDoc;

/**
 * @author Pablo
 *
 */
@FacesConverter(value = "categoriaVehiculoConverter")
public class CategoriaVehiculoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	CategoriaVehiculo categoriaVehiculo = new CategoriaVehiculo();
	categoriaVehiculo.setId(Integer.parseInt(toObject[0]));
	categoriaVehiculo.setNombre(toObject[1]);
	return categoriaVehiculo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof TipoDoc) {
	    CategoriaVehiculo categoriaVehiculo = (CategoriaVehiculo) value;
	    String idCategoriaVehiculo = Integer.toString(categoriaVehiculo.getId());
	    String nombreCategoriaVehiculo = categoriaVehiculo.getNombre();
	    String toString = idCategoriaVehiculo + ":" + nombreCategoriaVehiculo;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
    
}
