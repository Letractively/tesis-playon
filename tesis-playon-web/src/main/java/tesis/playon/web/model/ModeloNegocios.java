package tesis.playon.web.model;

import javax.annotation.security.RolesAllowed;

/**
 * 
 * @author gmorales
 *
 */
public class ModeloNegocios {

    @RolesAllowed(value = "ROLE_ADMIN")
    public String getUltimasNoticiasDelNegocio() {
	return "¡El proyecto va bien!";
    }
}