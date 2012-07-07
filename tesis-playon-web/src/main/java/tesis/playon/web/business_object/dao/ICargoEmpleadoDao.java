/**
 * 
 */
package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 *
 */
public interface ICargoEmpleadoDao {
    
    void save(CargoEmpleado cargo);

    void update(CargoEmpleado cargo);

    void delete(CargoEmpleado cargo);

    CargoEmpleado findByNombreCargo(String nombreCargo);

}
