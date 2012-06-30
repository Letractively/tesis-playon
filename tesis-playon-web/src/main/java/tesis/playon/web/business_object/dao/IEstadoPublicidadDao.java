package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.EstadoPublicidad;

public interface IEstadoPublicidadDao {

    void save(EstadoPublicidad estadoPublicidad);

    void update(EstadoPublicidad estadoPublicidad);

    void delete(EstadoPublicidad estadoPublicidad);

    EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad);

}
