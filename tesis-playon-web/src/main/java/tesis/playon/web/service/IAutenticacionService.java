package tesis.playon.web.service;

import javax.annotation.security.RolesAllowed;

/**
 * 
 * @author gmorales
 *
 */
public interface IAutenticacionService {

    boolean login(String username, String password);

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_REGISTERED" })
    void logout();

}
