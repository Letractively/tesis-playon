/**
 * 
 */
package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.ColorVehiculo;
import tesis.playon.web.model.EstadoPublicidad;

/**
 * @author Pablo
 *
 */
public interface IColorVehiculoDao {
    
    void save(ColorVehiculo color);

    void update(ColorVehiculo color);

    void delete(ColorVehiculo color);

    ColorVehiculo findByNombreColorVehiculo(String nombreColor);
    
    List<ColorVehiculo> findAll();
}