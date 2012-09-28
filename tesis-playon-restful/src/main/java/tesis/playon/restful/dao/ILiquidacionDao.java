package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Liquidacion;

public interface ILiquidacionDao {

    void save(Liquidacion liquidacion);
    
    void update(Liquidacion liquidacion);

    void delete(Liquidacion liquidacion);

    List<Liquidacion> findAll();
}
