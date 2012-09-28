package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.CategoriaVehiculo;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.Tarifa;

public interface ITarifaDao {

    void save(Tarifa tarifa);

    void update(Tarifa tarifa);

    void delete(Tarifa tarifa);

    int deleteTarifasPlaya(Playa playa);
    
    List<Tarifa> findByPlaya(Playa playa);

    List<Tarifa> findAll();

    List<Tarifa> findTarifaVigenteByPlaya(Playa playa);

    List<Tarifa> findTarifaVigenteByPlayaAndCategoriaVehiculo(Playa playa, CategoriaVehiculo categoriaVehiculo);

}
