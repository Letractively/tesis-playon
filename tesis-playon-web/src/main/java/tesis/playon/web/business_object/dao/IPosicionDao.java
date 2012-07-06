package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Posicion;

public interface IPosicionDao {

    void save(Posicion posicion);

    void update(Posicion posicion);

    void delete(Posicion posicion);

    Posicion findByNombrePosicion(String nombrePosicion);

}
