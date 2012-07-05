package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.MarcaVehiculo;

public interface IMarcaVehiculoDao {

    void save(MarcaVehiculo marcaVehiculo);

    void update(MarcaVehiculo marcaVehiculo);

    void delete(MarcaVehiculo marcaVehiculo);

    MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo);
}
