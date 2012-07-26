package tesis.playon.web.service;

import java.util.List;
import java.util.Set;

import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * 
 * @author gmorales
 *
 */
public interface IPaisService {

    void save(Pais pais);

    void update(Pais pais);

    void delete(Pais pais);

    List<Pais> findAll();

    Pais findByNombrPais(String nombrePais);

    Set<Provincia> findProvincia(Pais pais);
}