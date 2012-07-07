package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Publicidad;

/**
 * Interfaz DAO de Publicidad
 * @author alejandro
 * @date 07/07/2012
 */
public interface IPublicidadDao {

    void save(Publicidad publicidad);

    void update(Publicidad publicidad);

    void delete(Publicidad publicidad);
}
