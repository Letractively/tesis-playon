package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Localidad;

public interface ILocalidadDao {

    void save(Localidad pais);

    void update(Localidad pais);

    void delete(Localidad pais);

    Localidad findByNombreLocalidad(String nombreLocalidad);

}
