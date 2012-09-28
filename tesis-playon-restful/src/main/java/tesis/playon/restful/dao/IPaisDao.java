package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Pais;
import tesis.playon.restful.domain.Provincia;

public interface IPaisDao {

    void save(Pais pais);

    void update(Pais pais);

    void delete(Pais pais);

    Pais findByNombrePais(String nombrePais);
    
    Pais findByPaisId(Integer id);

    List<Pais> findAll();
    
    List<Provincia> getProvincias(Integer idPais);
}