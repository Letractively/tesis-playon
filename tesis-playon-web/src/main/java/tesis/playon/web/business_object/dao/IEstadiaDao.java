package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.Estadia;

public interface IEstadiaDao {

    void save(Estadia estadia);

    void update(Estadia estadia);

    void delete(Estadia estadia);

    List<Estadia> findAll();
}
