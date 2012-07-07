package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.TipoPago;

public interface ITipoPagoDao {

    void save(TipoPago tipoPago);
    
    void update(TipoPago tipoPago);
    
    void delete(TipoPago tipoPago);
    
    TipoPago findByNameTipoPago(String nombreTipoPago);
}
