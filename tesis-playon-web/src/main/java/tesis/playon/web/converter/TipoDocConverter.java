package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.TipoDoc;

@FacesConverter(value = "tipoDocConverter")
public class TipoDocConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext contxt, UIComponent component, String string) {
	String toObject[] = string.split(":");
	TipoDoc tipoDoc = new TipoDoc();
	tipoDoc.setId(Integer.parseInt(toObject[0]));
	tipoDoc.setNombre(toObject[1]);
	return tipoDoc;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objeto) {
	System.out.println("El objeto: " + ((TipoDoc) objeto).toString());
	if (objeto instanceof TipoDoc) {
	    TipoDoc tipoDoc = (TipoDoc) objeto;
	    String idTipoDoc = Integer.toString(tipoDoc.getId());
	    String nombreTipoDoc = tipoDoc.getNombre();
	    String toString = idTipoDoc + ":" + nombreTipoDoc;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}
