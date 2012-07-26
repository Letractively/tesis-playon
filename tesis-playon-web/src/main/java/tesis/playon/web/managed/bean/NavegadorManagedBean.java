package tesis.playon.web.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tesis.playon.web.model.ModeloNegocios;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "navegadorMB")
@SessionScoped
public class NavegadorManagedBean {

    private String paginaParaNavegar = "";

    @ManagedProperty(value = "modeloNegocios")
    private ModeloNegocios modeloNegocios = null;

    private String ultimasNoticias = null;

    public String navegarA() {
	if ("ToSecure".equalsIgnoreCase(paginaParaNavegar)) {
	    return "Secured";
	} else if ("ToUnSecure".equalsIgnoreCase(paginaParaNavegar)) {
	    return "UnSecured";
	} else {
	    return "none";
	}
    }

    public String showAdminPersonalPage() {
	this.setUltimasNoticias(this.getModeloNegocios().getUltimasNoticiasDelNegocio());
	return "adminPersonalDetails.xhtml";
    }

    public String getPaginaParaNavegar() {
	return paginaParaNavegar;
    }

    public void setPaginaParaNavegar(String paginaParaNavegar) {
	this.paginaParaNavegar = paginaParaNavegar;
    }

    public ModeloNegocios getModeloNegocios() {
	return modeloNegocios;
    }

    public void setModeloNegocios(ModeloNegocios modeloNegocios) {
	this.modeloNegocios = modeloNegocios;
    }

    public String getUltimasNoticias() {
	return ultimasNoticias;
    }

    public void setUltimasNoticias(String ultimasNoticias) {
	this.ultimasNoticias = ultimasNoticias;
    }

}