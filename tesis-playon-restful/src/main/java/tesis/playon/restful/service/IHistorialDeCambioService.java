package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.HistorialDeCambio;

public interface IHistorialDeCambioService {
    
    void save(HistorialDeCambio estadia);

    void update(HistorialDeCambio estadia);

    void delete(HistorialDeCambio estadia);

    List<HistorialDeCambio> findAll();

}
