package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Publicidad;

public interface IPublicidadDao {

    void save(Publicidad publicidad);

    void update(Publicidad publicidad);

    void delete(Publicidad publicidad);
    
    List<Publicidad> findAll();
}
