package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.DenunciaVehiculo;

public interface IDenunciaVehiculoDao {

    void save(DenunciaVehiculo denunciaVehiculo);

    void update(DenunciaVehiculo denunciaVehiculo);

    void delete(DenunciaVehiculo denunciaVehiculo);

    DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo);
    
    List<DenunciaVehiculo> findAll();
}
