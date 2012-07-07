/**
 * 
 */
package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Empleado;

/**
 * @author garribere
 *
 */
public interface IEmpleadoDao {

    void save(Empleado empleado);

    void update(Empleado empleado);

    void delete(Empleado empleado);

    Empleado findByLegajo(String legajo);
}
