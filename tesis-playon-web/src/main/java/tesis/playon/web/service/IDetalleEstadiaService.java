package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.DetalleEstadia;

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

}