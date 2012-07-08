package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.Playa;

public interface IPlayaDao {
    
    void save(Playa playa);
    
    void update(Playa playa);
    
    void delete(Playa playa);
    
    Playa findByNombreComercial(String nombreComercial);
    
    Playa findByRazonSocial(String razonSocial);
    
    List<Playa> findAll();

}
