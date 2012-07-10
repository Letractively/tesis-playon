package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;

public interface IEstadiaDao {

    void save(Estadia estadia);

    void update(Estadia estadia);

    void delete(Estadia estadia);
    
    Estadia findByPlaya(Playa playa);

    List<Estadia> findAll();
}
