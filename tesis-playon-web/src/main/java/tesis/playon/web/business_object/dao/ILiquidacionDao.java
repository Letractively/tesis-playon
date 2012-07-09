package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.Liquidacion;

public interface ILiquidacionDao {

    void save(Liquidacion liquidacion);
    
    void update(Liquidacion liquidacion);

    void delete(Liquidacion liquidacion);

    List<Liquidacion> findAll();
}
