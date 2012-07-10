package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.DenunciaVehiculo;

/**
 * @author Pablo
 *
 */
public interface IDenunciaVehiculoDao {

    void save(DenunciaVehiculo denunciaVehiculo);

    void update(DenunciaVehiculo denunciaVehiculo);

    void delete(DenunciaVehiculo denunciaVehiculo);

    DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo);
    
    List<DenunciaVehiculo> findAll();
}
