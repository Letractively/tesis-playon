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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.Calificacion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICalificacionService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "calificacionMB")
@RequestScoped
public class CalificacionManagedBean implements Serializable {

    private static final long serialVersionUID = 5047262325426946652L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{CalificacionService}")
    ICalificacionService calificacionService;

    private Integer calificacionPlaya;
    
    private Integer calificacion;

    private static Playa playaSelected;

    private List<Calificacion> calificacionListPerfil;
    
    private List<Calificacion> calificacionList;
    
    @PostConstruct
    private void init(){
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    calificacionList = new ArrayList<Calificacion>();
	    calificacionList = getCalificacionService().findByPlaya(user.getPlaya());
	    this.calificacion = 0;
	    if(calificacionList != null){
		for (Calificacion calificacion : calificacionList) {
		    this.calificacion += calificacion.getCalificacion();
		}
		calificacion = Math.round(calificacion / calificacionList.size());
	    }
	}
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public ICalificacionService getCalificacionService() {
	return calificacionService;
    }

    public void setCalificacionService(ICalificacionService calificacionService) {
	this.calificacionService = calificacionService;
    }

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Integer getCalificacionPlaya() {
	if(playaSelected != null){
	    calificacionListPerfil = new ArrayList<Calificacion>();
	    calificacionListPerfil = getCalificacionService().findByPlaya(playaSelected);
	    calificacionPlaya = 0;
	    if(calificacionListPerfil != null){
		for (Calificacion calificacion : calificacionListPerfil) {
		    calificacionPlaya += calificacion.getCalificacion();
		}
		calificacionPlaya = Math.round(calificacionPlaya / calificacionListPerfil.size());
	    }
	}
	return calificacionPlaya;
    }

    public void setCalificacionPlaya(Integer calificacionPlaya) {
	this.calificacionPlaya = calificacionPlaya;
    }

    public Playa getPlayaSelected() {
        return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	CalificacionManagedBean.playaSelected = playaSelected;
    }

    public List<Calificacion> getCalificacionListPerfil() {
	return calificacionListPerfil;
    }

    public void setCalificacionListPerfil(List<Calificacion> calificacionList) {
	this.calificacionListPerfil = calificacionList;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

}