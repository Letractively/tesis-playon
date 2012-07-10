package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Empleado;

/**
 * @author garribere
 *
 */
public interface IEmpleadoDao {

    void save(Empleado empleado);

    void update(Empleado empleado);

    void delete(Empleado empleado);

    Empleado findByLegajo(Integer legajo);
    
    List<Empleado> findAll();
}
