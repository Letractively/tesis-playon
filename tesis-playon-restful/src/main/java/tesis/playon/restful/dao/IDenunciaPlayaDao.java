package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.DenunciaPlaya;

public interface IDenunciaPlayaDao {

    void save(DenunciaPlaya denunciaPlaya);

    void update(DenunciaPlaya denunciaPlaya);

    void delete(DenunciaPlaya denunciaPlaya);

    DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya);
    
    List<DenunciaPlaya> findAll();
}
