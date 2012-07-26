package tesis.playon.web.dao;

import java.util.List;
import java.util.Set;

import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Provincia;

public interface IProvinciaDao {

    void save(Provincia provincia);

    void update(Provincia provincia);

    void delete(Provincia provincia);

    Provincia findByNombreProvincia(String nombreProvincia);

    List<Provincia> findAll();
    
    Set<Localidad> findLocalidades(Provincia provincia);
}
