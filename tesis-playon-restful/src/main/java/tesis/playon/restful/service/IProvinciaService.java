package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Pais;
import tesis.playon.restful.domain.Provincia;

public interface IProvinciaService {

    void save(Provincia provincia);

    void update(Provincia provincia);

    void delete(Provincia provincia);

    Provincia findByNombreProvincia(String nombreProvincia);
    
    Provincia findByProvinciaId(Integer id);

    List<Provincia> findAll();
    
    List<Provincia> findProvincias(Pais pais);
}
