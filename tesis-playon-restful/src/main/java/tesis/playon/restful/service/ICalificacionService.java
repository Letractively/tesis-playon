/**
 * 
 */
package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Calificacion;
import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Playa;

public interface ICalificacionService{
    
    void save(Calificacion calificacion);

    void update(Calificacion calificacion);

    void delete(Calificacion calificacion);

    List<Calificacion> findByPlaya(Playa playa);
    
    List<Calificacion> findAll();
    
    boolean isRate(Playa playa, Cliente cliente);

}
