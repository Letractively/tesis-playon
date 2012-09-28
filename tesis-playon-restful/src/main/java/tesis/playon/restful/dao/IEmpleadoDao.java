package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Empleado;
import tesis.playon.restful.domain.Usuario;

public interface IEmpleadoDao {

    void save(Empleado empleado);

    void update(Empleado empleado);

    void delete(Empleado empleado);

    List<Empleado> findAll();

    List<Empleado> findAll(Integer idCargoEmpleado);

    Empleado findById(Integer id);
    
    Empleado findByLegajo(Integer legajo);
    
    Empleado findByIdUsuario(Usuario usuario);

}
