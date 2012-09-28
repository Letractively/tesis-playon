package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Sesion;

public interface ISesionDao {

    void save(Sesion sesion);

    void update(Sesion sesion);

    void delete(Sesion sesion);

    Sesion findByIDSesionSesion(String idSesion);
    
    List<Sesion> findAll();
}
