package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.CargoEmpleado;

public interface ICargoEmpleadoService {

    void save(CargoEmpleado cargo);

    void update(CargoEmpleado cargo);

    void delete(CargoEmpleado cargo);

    CargoEmpleado findByNombreCargo(String nombreCargo);

    List<CargoEmpleado> findAll();

    CargoEmpleado findById(Integer id);
}
