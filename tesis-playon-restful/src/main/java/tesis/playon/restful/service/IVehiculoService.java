package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Vehiculo;

public interface IVehiculoService {

    void save(Vehiculo vehiculo);

    void update(Vehiculo vehiculo);

    void delete(Vehiculo vehiculo);

    Vehiculo findByPatenteVehiculo(String patenteVehiculo);

    List<Vehiculo> findAll();

    List<Vehiculo> findByCliente(int idCliente);

}
