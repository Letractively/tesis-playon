package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Posicion;

public interface IPosicionService {

    void save(Posicion posicion);

    void update(Posicion posicion);

    void delete(Posicion posicion);

    Posicion findByUbicacion(String ubicacion);

    List<Posicion> findAll();

}
