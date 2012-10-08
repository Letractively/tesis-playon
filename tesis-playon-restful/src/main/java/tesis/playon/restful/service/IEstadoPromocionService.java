package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.EstadoPromocion;

public interface IEstadoPromocionService {

    void save(EstadoPromocion estadoPromocion);

    void update(EstadoPromocion estadoPromocion);

    void delete(EstadoPromocion estadoPromocion);

    EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion);

    List<EstadoPromocion> findAll();
}
