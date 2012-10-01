/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;

/**
 * @author pablo
 *
 */
public interface IPromocionService {

    void save(Promocion promocion);

    void update(Promocion promocion);

    void delete(Promocion promocion);

    Promocion findByNombrePromocion(String nombrePromocion);

    List<Promocion> findAll();
    
    List<Promocion> findByEstado(EstadoPromocion estado);
    
    List<Promocion> findByPlaya(Playa playa, EstadoPromocion estado);
    
    List<Promocion> findByPlaya(Playa playa);
}
