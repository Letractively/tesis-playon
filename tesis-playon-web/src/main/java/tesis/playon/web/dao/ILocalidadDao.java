package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Localidad;

public interface ILocalidadDao {

    void save(Localidad pais);

    void update(Localidad pais);

    void delete(Localidad pais);

    Localidad findByNombreLocalidad(String nombreLocalidad);

    List<Localidad> findAll();
}
