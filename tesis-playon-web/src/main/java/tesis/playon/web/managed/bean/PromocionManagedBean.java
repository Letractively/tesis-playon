/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.service.IBarrioService;

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
