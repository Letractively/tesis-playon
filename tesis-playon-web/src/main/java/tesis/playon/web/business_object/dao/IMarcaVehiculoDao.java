package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.MarcaVehiculo;
import java.util.List;

public interface IMarcaVehiculoDao {

    void save(MarcaVehiculo marcaVehiculo);

    void update(MarcaVehiculo marcaVehiculo);

    void delete(MarcaVehiculo marcaVehiculo);

    MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo);
    
    List<MarcaVehiculo> findAll();
}
