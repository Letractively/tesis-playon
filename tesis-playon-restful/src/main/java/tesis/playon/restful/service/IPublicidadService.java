package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Publicidad;

public interface IPublicidadService {

    void save(Publicidad publicidad);

    void update(Publicidad publicidad);

    void delete(Publicidad publicidad);
    
    List<Publicidad> findAll();
}
