/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;

/**
 * @author pablo
 * 
 */
public interface IPlayaService {

    void save(Playa playa);

    void update(Playa playa);

    void delete(Playa playa);

    Playa findByNombreComercial(String nombreComercial);

    Playa findByRazonSocial(String razonSocial);

    List<Playa> findAll();

    List<Playa> findPlayasCercanas(Double longitud, Double latitud, int distancia);

    List<Playa> findByEstado(EstadoPlaya estado);

}