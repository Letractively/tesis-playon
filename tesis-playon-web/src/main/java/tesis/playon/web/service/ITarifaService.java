package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Tarifa;

/**
 * 
 * @author gmorales
 * 
 */
public interface ITarifaService {

    void save(Tarifa tarifa);

    void update(Tarifa tarifa);

    void delete(Tarifa tarifa);

    List<Tarifa> findAll();

}