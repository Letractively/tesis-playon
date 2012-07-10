package tesis.playon.web.dao;

import tesis.playon.web.model.Vehiculo;

/**
 * @author Pablo
 *
 */
public interface IVehiculoDao {

    void save(Vehiculo vehiculo);

    void update(Vehiculo vehiculo);

    void delete(Vehiculo vehiculo);

    Vehiculo findByPatenteVehiculo(String patenteVehiculo);
    
}
