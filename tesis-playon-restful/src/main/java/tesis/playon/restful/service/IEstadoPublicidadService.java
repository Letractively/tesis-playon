package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.EstadoPublicidad;

public interface IEstadoPublicidadService {

    void save(EstadoPublicidad estadoPublicidad);

    void update(EstadoPublicidad estadoPublicidad);

    void delete(EstadoPublicidad estadoPublicidad);

    EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad);
    
    List<EstadoPublicidad> findAll();
}
