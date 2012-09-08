package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Playa;
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

    List<Tarifa> findByPlaya(Playa playa);

    List<Tarifa> findAll();

    List<Tarifa> findTarifaVigenteByPlaya(Playa playa);

    List<Tarifa> findTarifaVigenteByPlayaAndCategoriaVehiculo(Playa playa, CategoriaVehiculo categoriaVehiculo);
}