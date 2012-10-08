package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Liquidacion;

public interface ILiquidacionService {

    void save(Liquidacion liquidacion);
    
    void update(Liquidacion liquidacion);

    void delete(Liquidacion liquidacion);

    List<Liquidacion> findAll();
}
