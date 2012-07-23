package tesis.playon.web.service.impl;

import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tesis.playon.web.service.IAutenticacionService;

/**
 * 
 * @author gmorales
 * 
 */
@Service("autenticacionService")
public class AutenticacionService implements IAutenticacionService {

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(String username, String password) {
	try {
	    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
		    username, password));
	    if (authenticate.isAuthenticated()) {
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return true;
	    }
	} catch (AuthenticationException e) {
	    e.toString();
	}
	return false;
    }

    @Override
    public void logout() {
	SecurityContextHolder.getContext().setAuthentication(null);
    }

}
