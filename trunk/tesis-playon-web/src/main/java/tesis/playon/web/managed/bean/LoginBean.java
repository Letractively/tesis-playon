package tesis.playon.web.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "loginMB")
@RequestScoped
public class LoginBean {

    private static final String ROLE_ADMIN = "[ROLE_ADMIN]";

    private static final String ROLE_AUDITOR = "[ROLE_AUDITOR]";

    private static final String ROLE_CONTADOR = "[ROLE_CONTADOR]";

    private static final String ROLE_USER = "[ROLE_USER]";

    private static final String ROLE_CLIENT = "[ROLE_CLIENT]";

    private static final String ROLE_PLAYA_EMPLEADO = "[ROLE_PLAYA_EMPLEADO]";

    private static final String ROLE_PLAYA_GERENTE = "[ROLE_PLAYA_GERENTE]";

    private String usuario = null;

    private String password = null;

    private String recordar = null;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    @ManagedProperty(value = "#{rememberMeServices}")
    private RememberMeServices rememberMeServices = null;

    @ManagedProperty(value = "#{customJDBCUserService}")
    private UserDetailsService userDetailsService = null;

    public String login() {
	Authentication result = null;
	try {
	    if ("TRUE".equalsIgnoreCase(this.getRecordar())) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsuario());
		RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(
			"jsfspring-sec", userDetails, userDetails.getAuthorities());
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
			.getExternalContext().getRequest();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
			.getExternalContext().getResponse();
		rememberMeServices.loginSuccess(httpServletRequest, httpServletResponse, rememberMeAuthenticationToken);
		result = rememberMeAuthenticationToken;
	    } else {
		Authentication request = new UsernamePasswordAuthenticationToken(this.getUsuario(), this.getPassword());
		result = authenticationManager.authenticate(request);
	    }
	    SecurityContextHolder.getContext().setAuthentication(result);
	    if (ROLE_ADMIN.equals(result.getAuthorities().toString())) {
		return "SecuredAdmin";
	    } else if (ROLE_CLIENT.equals(result.getAuthorities().toString())) {
		return "SecuredClient";
	    } else {
		return "SecuredClient";
	    }
	} catch (AuthenticationException e) {
	    e.printStackTrace();
	    FacesMessage fm = new FacesMessage("Usuario o contraseña incorrecto");
	    FacesContext.getCurrentInstance().addMessage("Usuario o contraseña incorrecto", fm);
	    return "UnSecured";
	}

    }

    public String logout() {
	SecurityContextHolder.clearContext();
	// Delete Cookies
	HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
		.getExternalContext().getRequest();
	HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
		.getExternalContext().getResponse();
	Cookie cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE", null);
	cookie.setMaxAge(0);
	cookie.setPath(httpServletRequest.getContextPath().length() > 0 ? httpServletRequest.getContextPath() : "/");
	httpServletResponse.addCookie(cookie);
	// return "LoggedOut";
	return "index?faces-redirect=true";
    }

    public String cancel() {
	return "index";
    }

    public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }

    public RememberMeServices getRememberMeServices() {
	return rememberMeServices;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
	this.rememberMeServices = rememberMeServices;
    }

    public UserDetailsService getUserDetailsService() {
	return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
	this.userDetailsService = userDetailsService;
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

    public String getRecordar() {
	return recordar;
    }

    public void setRecordar(String recordar) {
	this.recordar = recordar;
    }

}