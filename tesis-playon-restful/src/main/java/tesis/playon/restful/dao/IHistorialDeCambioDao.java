package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.HistorialDeCambio;

public interface IHistorialDeCambioDao {
    
    void save(HistorialDeCambio estadia);

    void update(HistorialDeCambio estadia);

    void delete(HistorialDeCambio estadia);

    List<HistorialDeCambio> findAll();

}
