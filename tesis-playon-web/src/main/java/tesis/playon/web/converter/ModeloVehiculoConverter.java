package tesis.playon.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;

@FacesConverter(value = "modeloVehiculoConverter")
public class ModeloVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (!StringUtils.isEmpty(value)) {
	    String toObject[] = value.split(":");
	    if (toObject.length != 4) {
		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_WARN,
			"Debe seleccionar una opción", null));
		// return null;
	    } else {
		ModeloVehiculo modelo = new ModeloVehiculo();
		modelo.setId(Integer.parseInt(toObject[0]));
		modelo.setNombre(toObject[1]);

		CategoriaVehiculo categoria = new CategoriaVehiculo();
		categoria.setNombre(toObject[3].toString());
		categoria.setId(Integer.parseInt(toObject[2]));
		modelo.setCategoriaVehiculo(categoria);
		return modelo;
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value != null && !value.equals("")) {
	    if (value instanceof ModeloVehiculo) {
		ModeloVehiculo modelo = (ModeloVehiculo) value;
		String idModelo = Integer.toString(modelo.getId());
		String nombreModelo = modelo.getNombre();
		String idCategoria = Integer.toString(modelo.getCategoriaVehiculo().getId());
		String cat = modelo.getCategoriaVehiculo().getNombre();
		String toString = idModelo + ":" + nombreModelo + ":" + idCategoria + ":" + cat;
		return toString;
	    } else {
		System.out.println("No se pudo parsear el objeto");
		return null;
	    }
	}
	return null;
    }

}
