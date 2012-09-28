package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.CategoriaVehiculo;

public interface ICategoriaVehiculoDao {

    void save(CategoriaVehiculo categoria);

    void update(CategoriaVehiculo categoria);

    void delete(CategoriaVehiculo categoria);

    CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoria);
    
    List<CategoriaVehiculo> findAll();
}
