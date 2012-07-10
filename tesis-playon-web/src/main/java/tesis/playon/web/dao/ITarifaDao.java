package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;

public interface ITarifaDao {

    void save(Tarifa tarifa);

    void update(Tarifa tarifa);

    void delete(Tarifa tarifa);

    Tarifa findByPlayaID(Playa playa);

    List<Tarifa> findAll();
}
