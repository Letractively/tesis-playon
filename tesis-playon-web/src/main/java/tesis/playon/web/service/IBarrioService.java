package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Barrio;

/**
 * 
 * @author gmorales
 *
 */
public interface IBarrioService {

    void save(Barrio barrio);

    void update(Barrio barrio);

    void delete(Barrio barrio);

    List<Barrio> findAll();

    Barrio findByNombreBarrio(String nombreBarrio);

}