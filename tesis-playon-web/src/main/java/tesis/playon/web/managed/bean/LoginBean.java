package tesis.playon.web.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "loginMB")
@RequestScoped
public class LoginBean {

    private String usuario = null;

    private String password = null;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    public String login() {
	try {
	    Authentication request = new UsernamePasswordAuthenticationToken(this.getUsuario(), this.getPassword());
	    Authentication result = authenticationManager.authenticate(request);
	    SecurityContextHolder.getContext().setAuthentication(result);
	} catch (AuthenticationException e) {
	    e.printStackTrace();
	}
	return "Secured";
    }

    public String logout() {
	SecurityContextHolder.clearContext();
	return "loggedout";
    }

    public String cancel() {
	return null;
    }

    public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
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