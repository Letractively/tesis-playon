package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Foto;
import tesis.playon.restful.domain.PerfilPlaya;

public interface IFotoService {
    
    void save(Foto foto);

    void update(Foto foto);

    void delete(Foto foto);

    Foto findByLinkFoto(String link);
    
    List<Foto> findAll();
    
    Integer obtenerUltimoID();
    
    List<Foto> findByPlaya(PerfilPlaya perfil);
}
