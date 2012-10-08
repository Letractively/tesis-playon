package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Estadia;
import tesis.playon.restful.domain.Playa;

public interface IEstadiaService {

    void save(Estadia estadia);

    void update(Estadia estadia);

    void delete(Estadia estadia);
    
    List<Estadia> findAll();
    
    Estadia findByPlaya(Playa playa);
    
}