package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.CategoriaVehiculo;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.Tarifa;

public interface ITarifaService {

    void save(Tarifa tarifa);

    void update(Tarifa tarifa);

    void delete(Tarifa tarifa);

    int deleteTarifasPlaya(Playa playa);

    List<Tarifa> findByPlaya(Playa playa);

    List<Tarifa> findAll();

    List<Tarifa> findTarifaVigenteByPlaya(String nombreComercialPlaya);

    List<Tarifa> findTarifaVigenteByPlayaAndCategoriaVehiculo(Playa playa, CategoriaVehiculo categoriaVehiculo);

}