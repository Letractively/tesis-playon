package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.service.IAutenticacionService;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -4825514920026120523L;

    private String login;

    private String password;

    @ManagedProperty(value = "#{AutenticacionService}")
    private IAutenticacionService autenticacionService;

    public String login() {

	// ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	// RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("");
	// dispatcher.forward((ServletRequest) context.getContext(), (ServletResponse) context.getResponse());
	// FacesContext.getCurrentInstance().responseComplete();

	boolean success = autenticacionService.login(login, password);

	if (success) {
	    return "pages/usuariolist.xhtml";
	} else {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario o contrasena incorrecta."));
	    return "pages/login.xhtml";
	}
    }

    public String logout() {
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "pages/logout.xhtml";
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setAuthenticationService(IAutenticacionService autenticacionService) {
	this.autenticacionService = autenticacionService;
    }

}
