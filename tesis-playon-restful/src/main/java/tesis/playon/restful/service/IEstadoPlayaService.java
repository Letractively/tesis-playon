package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.EstadoPlaya;

public interface IEstadoPlayaService {

    void save(EstadoPlaya estadoPlaya);

    void update(EstadoPlaya estadoPlaya);

    void delete(EstadoPlaya estadoPlaya);

    EstadoPlaya findByNombreEstadoPlaya(String nombre);

    List<EstadoPlaya> findAll();
}
