package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.CargoEmpleado;

public interface ICargoEmpleadoDao {

    void save(CargoEmpleado cargo);

    void update(CargoEmpleado cargo);

    void delete(CargoEmpleado cargo);

    CargoEmpleado findByNombreCargo(String nombreCargo);

    List<CargoEmpleado> findAll();

    CargoEmpleado findById(Integer id);
}
