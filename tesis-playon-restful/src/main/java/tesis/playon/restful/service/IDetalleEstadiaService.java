package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.DetalleEstadia;
import tesis.playon.restful.domain.Vehiculo;

public interface IDetalleEstadiaService {

    void save(DetalleEstadia denunciaPlaya);

    void update(DetalleEstadia denunciaPlaya);

    void delete(DetalleEstadia denunciaPlaya);

    List<DetalleEstadia> findAll();

    DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo);

}
