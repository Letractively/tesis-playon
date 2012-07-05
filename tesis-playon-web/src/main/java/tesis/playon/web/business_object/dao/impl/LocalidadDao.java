package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ILocalidadDao;
import tesis.playon.web.model.Localidad;

/**
 * 
 * @author gmorales
 *
 */
@Repository("localidadDao")
public class LocalidadDao extends CustomHibernateDaoSupport implements ILocalidadDao {

    public void save(Localidad localidad) {
	getHibernateTemplate().save(localidad);
    }

    public void update(Localidad localidad) {
	getHibernateTemplate().update(localidad);
    }

    public void delete(Localidad localidad) {
	getHibernateTemplate().delete(localidad);
    }

    public Localidad findByNombreLocalidad(String nombreLocalidad) {
	List<?> list = getHibernateTemplate().find("from Localidad where nombre=?", nombreLocalidad);
	return (Localidad) list.get(0);
    }

}
