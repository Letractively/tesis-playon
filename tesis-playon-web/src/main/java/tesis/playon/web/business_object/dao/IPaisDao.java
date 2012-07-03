package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Pais;

public interface IPaisDao {

    void save(Pais pais);

    void update(Pais pais);

    void delete(Pais pais);

    Pais findByNombrePais(String nombrePais);

}
