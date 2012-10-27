package tesis.playon.web.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Vehiculo;

/**
 * 
 * @author gmorales
 * 
 */
public interface IDetalleEstadiaDao {

    void save(DetalleEstadia denunciaPlaya);

    void update(DetalleEstadia denunciaPlaya);

    void delete(DetalleEstadia denunciaPlaya);

    List<DetalleEstadia> findAll();

    DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo);

    List<DetalleEstadia> findByEstadia(Estadia estadia);

    List<DetalleEstadia> findByHorarios(Estadia estadia, Date horaInicio, Date horaFin);

}
