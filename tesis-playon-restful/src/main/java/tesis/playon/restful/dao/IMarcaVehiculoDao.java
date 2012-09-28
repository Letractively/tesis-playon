package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.MarcaVehiculo;

public interface IMarcaVehiculoDao {

    void save(MarcaVehiculo marcaVehiculo);

    void update(MarcaVehiculo marcaVehiculo);

    void delete(MarcaVehiculo marcaVehiculo);

    MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo);
    
    List<MarcaVehiculo> findAll();
}
