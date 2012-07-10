package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.ILocalidadDao;
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

    public List<Localidad> findAll(){
	List<Localidad> localidades = new ArrayList<Localidad>();
	List<?> list = getHibernateTemplate().find("from Localidad");
	for (Object object : list) {
	    localidades.add((Localidad)object);
	}
	return localidades;
    }
}
