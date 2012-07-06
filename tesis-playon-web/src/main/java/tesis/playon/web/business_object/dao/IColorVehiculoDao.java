/**
 * 
 */
package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.ColorVehiculo;

/**
 * @author Pablo
 *
 */
public interface IColorVehiculoDao {
    
    void save(ColorVehiculo color);

    void update(ColorVehiculo color);

    void delete(ColorVehiculo color);

    ColorVehiculo findByNombreColorVehiculo(String nombreColor);
}
