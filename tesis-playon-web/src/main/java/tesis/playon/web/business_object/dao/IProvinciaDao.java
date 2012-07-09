package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.Provincia;

public interface IProvinciaDao {

    void save(Provincia provincia);

    void update(Provincia provincia);

    void delete(Provincia provincia);

    Provincia findByNombreProvincia(String nombreProvincia);

    List<Provincia> findAll();
}
