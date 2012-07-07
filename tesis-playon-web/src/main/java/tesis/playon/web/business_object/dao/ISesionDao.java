package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Sesion;

/**
 * 
 * @author alejandro
 * @date 07/07/2012
 */
public interface ISesionDao {

    void save(Sesion sesion);

    void update(Sesion sesion);

    void delete(Sesion sesion);

    Sesion findByIDSesionSesion(String idSesion);
}
