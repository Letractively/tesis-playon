package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.DetalleEstadia;

public interface IDetalleEstadiaDao {

    void save(DetalleEstadia denunciaPlaya);

    void update(DetalleEstadia denunciaPlaya);

    void delete(DetalleEstadia denunciaPlaya);
    
    List<DetalleEstadia> findAll();
}