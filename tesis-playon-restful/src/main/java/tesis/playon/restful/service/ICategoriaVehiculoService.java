package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.CategoriaVehiculo;

public interface ICategoriaVehiculoService {

    void save(CategoriaVehiculo categoria);

    void update(CategoriaVehiculo categoria);

    void delete(CategoriaVehiculo categoria);

    CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoria);
    
    List<CategoriaVehiculo> findAll();
}
