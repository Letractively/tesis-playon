package tesis.playon.web.managed.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

/**
 * 
 * @author alejandro
 *
 */
@ManagedBean(name = "documentExporterMB")
@RequestScoped
public class DocumentExporterManagedBean implements Serializable {

    private static final long serialVersionUID = 1962213188760415313L;

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
	String sep = File.separator;
	Document pdf = (Document) document;
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	
	String logo = extContext.getRealPath("resources" + sep + "images" +sep + "transacciones.png");
	String titulo = params.get("titulo");
	
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	pdf.addTitle(titulo);
	pdf.add(Image.getInstance(logo));
    }
}
