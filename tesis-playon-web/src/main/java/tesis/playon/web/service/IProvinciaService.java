package tesis.playon.web.service;

import java.util.List;
import java.util.Set;

import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Provincia;

/**
 * 
 * @author gmorales
 *
 */
public interface IProvinciaService {

    void save(Provincia provincia);

    void update(Provincia provincia);

    void delete(Provincia provincia);

    List<Provincia> findAll();

    Provincia findByNombreProvincia(String nombreProvincia);

    Set<Localidad> findLocalidades(Provincia provincia);
}