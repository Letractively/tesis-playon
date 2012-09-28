package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Promocion;

public interface IPromocionDao {

    void save(Promocion promocion);

    void update(Promocion promocion);

    void delete(Promocion promocion);

    Promocion findByNombrePromocion(String nombrePromocion);

    List<Promocion> findAll();
}
