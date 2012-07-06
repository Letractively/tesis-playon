package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Tarifa;

public interface ITarifaDao {

    void save(Tarifa tarifa);

    void update(Tarifa tarifa);

    void delete(Tarifa tarifa);

    Tarifa findByNombreTarifa(String nombreTarifa);

}
