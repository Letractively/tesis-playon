package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Estadia;

/**
 * 
 * @author gmorales
 *
 */
public interface IEstadiaService {

    void save(Estadia estadia);

    void update(Estadia estadia);

    void delete(Estadia estadia);

    List<Estadia> findAll();

}