/**
 * 
 */
package tesis.playon.web.business_object.dao;

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
}
