/**
 * 
 */
package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.ModeloVehiculo;

/**
 * @author Pablo
 *
 */
public interface IModeloVehiculoDao {

    void save(ModeloVehiculo modelo);

    void update(ModeloVehiculo modelo);

    void delete(ModeloVehiculo modelo);

    ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo);
}
