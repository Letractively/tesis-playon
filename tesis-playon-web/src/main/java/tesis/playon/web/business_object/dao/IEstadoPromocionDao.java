package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.EstadoPromocion;

public interface IEstadoPromocionDao {

    void save(EstadoPromocion estadoPromocion);

    void update(EstadoPromocion estadoPromocion);

    void delete(EstadoPromocion estadoPromocion);

    EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion);

}
