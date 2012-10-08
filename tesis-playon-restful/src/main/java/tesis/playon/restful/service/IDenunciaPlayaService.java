package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.DenunciaPlaya;

public interface IDenunciaPlayaService {

    void save(DenunciaPlaya denunciaPlaya);

    void update(DenunciaPlaya denunciaPlaya);

    void delete(DenunciaPlaya denunciaPlaya);

    DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya);
    
    List<DenunciaPlaya> findAll();
}
