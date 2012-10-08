package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.DenunciaVehiculo;

public interface IDenunciaVehiculoService {

    void save(DenunciaVehiculo denunciaVehiculo);

    void update(DenunciaVehiculo denunciaVehiculo);

    void delete(DenunciaVehiculo denunciaVehiculo);

    DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo);
    
    List<DenunciaVehiculo> findAll();
}
