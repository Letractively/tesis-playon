package tesis.playon.web.managed.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -4825514920026120523L;

    private String usuario;

    private String password;

    // @ManagedProperty(value = "#{AutenticacionService}")
    // private IAutenticacionService autenticacionService;

    public String login() throws IOException, ServletException {

	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

	RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
		.getRequestDispatcher("/j_spring_security_check");

	dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

	FacesContext.getCurrentInstance().responseComplete();
	// It's OK to return null here because Faces is just going to exit.
	return null;

	// boolean success = autenticacionService.login(login, password);

	// if (success) {
	// return "pages/usuariolist.xhtml";
	// } else {
	// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario o contrasena incorrecta."));
	// return "pages/login.xhtml";
	// }
    }

    public String logout() {
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "logout.xhtml";
    }

    public String getUsuario() {
	return usuario;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
