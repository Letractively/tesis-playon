/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.DenunciaVehiculo;

/**
 * @author pablo
 * 
 */
public interface IDenunciaVehiculoService {

    void save(DenunciaVehiculo denuncia);

    void update(DenunciaVehiculo denuncia);

    void delete(DenunciaVehiculo denuncia);

    DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo);

    List<DenunciaVehiculo> findAll();

}
