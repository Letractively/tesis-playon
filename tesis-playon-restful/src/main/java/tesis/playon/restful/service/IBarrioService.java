package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Barrio;

public interface IBarrioService {

    void save(Barrio barrio);

    void update(Barrio barrio);

    void delete(Barrio barrio);

    Barrio findByNombreBarrio(String nombreBarrio);

    List<Barrio> findAll();
}
