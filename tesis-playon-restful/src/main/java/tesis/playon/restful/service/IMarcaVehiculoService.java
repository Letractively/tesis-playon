package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.MarcaVehiculo;

public interface IMarcaVehiculoService {

    void save(MarcaVehiculo marcaVehiculo);

    void update(MarcaVehiculo marcaVehiculo);

    void delete(MarcaVehiculo marcaVehiculo);

    MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo);
    
    List<MarcaVehiculo> findAll();
}
