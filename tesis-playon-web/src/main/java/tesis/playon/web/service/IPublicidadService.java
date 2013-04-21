/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Publicidad;

/**
 * @author pablo
 *
 */
public interface IPublicidadService {

    void save(Publicidad publicidad);

    void update(Publicidad publicidad);

    void delete(Publicidad publicidad);
    
    List<Publicidad> findAll();
    
    List<Publicidad> findAllByEstadoVigente();
    
}
