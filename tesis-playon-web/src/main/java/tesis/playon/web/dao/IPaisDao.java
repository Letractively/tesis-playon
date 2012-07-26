package tesis.playon.web.dao;

import java.util.List;
import java.util.Set;

import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

public interface IPaisDao {

    void save(Pais pais);

    void update(Pais pais);

    void delete(Pais pais);

    Pais findByNombrePais(String nombrePais);

    List<Pais> findAll();
    
    Set<Provincia> findProvincia(Pais pais);
}