package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IPosicionDao;
import tesis.playon.web.model.Posicion;

/**
 * 
 * @author gmorales
 * 
 */
@Repository("posicionDao")
public class PosicionDao extends CustomHibernateDaoSupport implements IPosicionDao {

    public void save(Posicion posicion) {
	getHibernateTemplate().save(posicion);
    }

    public void update(Posicion posicion) {
	getHibernateTemplate().update(posicion);
    }

    public void delete(Posicion posicion) {
	getHibernateTemplate().delete(posicion);
    }

    public Posicion findByNombrePosicion(String nombrePosicion) {
	List<?> list = getHibernateTemplate().find("from Posicion where nombre=?", nombrePosicion);
	return (Posicion) list.get(0);
    }

}
