package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.MarcaVehiculo;
import tesis.playon.restful.domain.ModeloVehiculo;

public interface IModeloVehiculoService {

    void save(ModeloVehiculo modelo);

    void update(ModeloVehiculo modelo);

    void delete(ModeloVehiculo modelo);

    ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo);
    
    List<ModeloVehiculo> findByMarca(MarcaVehiculo marca);
    
    List<ModeloVehiculo> findAll();
}
