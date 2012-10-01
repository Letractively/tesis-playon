/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "validatorMB")
@RequestScoped
public class ValidatorManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    public void isValidEmailUsuario(FacesContext context, UIComponent component, Object value) {
	String email = (String) value;
	if (getUsuarioService().existeEmail(email)) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email " + email
		    + " ya se encuentra registrado.", null);
	    throw new ValidatorException(message);
	}
    }
    
    public void isValidUserName(FacesContext context, UIComponent component, Object value) {
	String userName = (String) value;
	if (getUsuarioService().existeUserName(userName)) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre de usuario " 
		    + userName + " ya se encuentra registrado.", null);
	    throw new ValidatorException(message);
	}
    }
    
    public void isValidEmailPlaya(FacesContext context, UIComponent component, Object value) {
	String email = (String) value;
	if (getPlayaService().existeEmail(email)) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email " + email
		    + " ya se encuentra registrado.", null);
	    throw new ValidatorException(message);
	}
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }
}
