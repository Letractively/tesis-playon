/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "promocionMB")
@RequestScoped
public class PromocionManagedBean implements Serializable {
    
    private static final long serialVersionUID = 6773490680356877684L;

    private static final String LISTA_PROMOCIONES = "promocioneslist";

    private static final String ERROR = "error";
    
    private String nombre;

}
