package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Vehiculo;

/**
 * 
 * @author gmorales
 * 
 */
public interface IDetalleEstadiaService {

    void save(DetalleEstadia detalleEstadia);

    void update(DetalleEstadia detalleEstadia);

    void delete(DetalleEstadia detalleEstadia);

    List<DetalleEstadia> findAll();

    DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo);

}