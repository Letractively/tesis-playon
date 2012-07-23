package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.EstadoPlaya;

/**
 * 
 * @author gmorales
 *
 */
public interface IEstadoPlayaService {

    void save(EstadoPlaya EstadoPlaya);

    void update(EstadoPlaya EstadoPlaya);

    void delete(EstadoPlaya EstadoPlaya);

    List<EstadoPlaya> findAll();

    EstadoPlaya findByNombreEstadoPlaya(String nombreEstadoPlaya);

}