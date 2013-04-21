/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tesis.playon.web.model.FotoPublicidad;
import tesis.playon.web.model.Publicidad;
import tesis.playon.web.service.IFotoPublicidadService;
import tesis.playon.web.service.IPublicidadService;
import tesis.playon.web.util.WriteImage;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "fotoPublicidadMB")
@ViewScoped
public class FotoPublicidadManagedBean implements Serializable {

    private static final long serialVersionUID = 7191837117945230509L;

    @ManagedProperty(value = "#{FotoPublicidadService}")
    IFotoPublicidadService fotoPublicidadService;

    @ManagedProperty(value = "#{PublicidadService}")
    IPublicidadService publicidadService;

    private static List<FotoPublicidad> fotoPublicidadList;
    
    private static List<Publicidad> publicidadList;

    @PostConstruct
    private void init() {
	publicidadList = getPublicidadService().findAllByEstadoVigente();
	fotoPublicidadList = new ArrayList<FotoPublicidad>();

	if (publicidadList != null) {

	    for (Publicidad publicidad : publicidadList) {
		fotoPublicidadList.add(publicidad.getFotoPublicidad());
	    }
	    if (fotoPublicidadList != null) {
		WriteImage.writeFotosPublicidad(fotoPublicidadList);
	    }
	}
    }

    /**
     * @return the fotoPublicidadService
     */
    public IFotoPublicidadService getFotoPublicidadService() {
	return fotoPublicidadService;
    }

    /**
     * @param fotoPublicidadService
     *            the fotoPublicidadService to set
     */
    public void setFotoPublicidadService(IFotoPublicidadService fotoPublicidadService) {
	this.fotoPublicidadService = fotoPublicidadService;
    }

    /**
     * @return the publicidadService
     */
    public IPublicidadService getPublicidadService() {
	return publicidadService;
    }

    /**
     * @param publicidadService
     *            the publicidadService to set
     */
    public void setPublicidadService(IPublicidadService publicidadService) {
	this.publicidadService = publicidadService;
    }

    /**
     * @return the fotoPublicidadList
     */
    public List<FotoPublicidad> getFotoPublicidadList() {
	return fotoPublicidadList;
    }

    /**
     * @param fotoPublicidadList
     *            the fotoPublicidadList to set
     */
    public void setFotoPublicidadList(List<FotoPublicidad> fotoPublicidadList) {
	FotoPublicidadManagedBean.fotoPublicidadList = fotoPublicidadList;
    }

    /**
     * @return the publicidadList
     */
    public List<Publicidad> getPublicidadList() {
        return publicidadList;
    }

    /**
     * @param publicidadList the publicidadList to set
     */
    public void setPublicidadList(List<Publicidad> publicidadList) {
	FotoPublicidadManagedBean.publicidadList = publicidadList;
    }
}
