package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.ColorVehiculo;

public interface IColorVehiculoDao {
    
    void save(ColorVehiculo color);

    void update(ColorVehiculo color);

    void delete(ColorVehiculo color);

    ColorVehiculo findByNombreColorVehiculo(String nombreColor);
    
    List<ColorVehiculo> findAll();
}
