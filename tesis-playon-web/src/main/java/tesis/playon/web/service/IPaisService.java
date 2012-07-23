package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Pais;

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

}