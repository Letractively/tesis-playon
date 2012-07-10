package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Promocion;

/**
 * @author Pablo
 *
 */
public interface IPromocionDao {

    void save(Promocion promocion);

    void update(Promocion promocion);

    void delete(Promocion promocion);

    Promocion findByNombrePromocion(String nombrePromocion);

    List<Promocion> findAll();
}
