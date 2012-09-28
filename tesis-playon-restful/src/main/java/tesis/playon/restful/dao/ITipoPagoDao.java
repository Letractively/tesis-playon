package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.TipoPago;

public interface ITipoPagoDao {

    void save(TipoPago tipoPago);
    
    void update(TipoPago tipoPago);
    
    void delete(TipoPago tipoPago);
    
    TipoPago findByNameTipoPago(String nombreTipoPago);
    
    List<TipoPago> findAll();
}
