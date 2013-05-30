package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.PerfilPlaya;

public interface IPerfilPlayaService {

    void save(PerfilPlaya perfilPlaya);

    void update(PerfilPlaya perfilPlaya);

    void delete(PerfilPlaya perfilPlaya);

    PerfilPlaya findByPlaya(String nombreComercialPlaya);

    List<PerfilPlaya> findAll();

}
