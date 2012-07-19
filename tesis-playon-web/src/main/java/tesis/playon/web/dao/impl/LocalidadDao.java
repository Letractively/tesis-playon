package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ILocalidadDao;
import tesis.playon.web.model.Localidad;

/**
 * 
 * @author gmorales
 * 
 */
public class LocalidadDao implements ILocalidadDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Localidad localidad) {
	getSessionFactory().getCurrentSession().save(localidad);
    }

    public void update(Localidad localidad) {
	getSessionFactory().getCurrentSession().update(localidad);
    }

    public void delete(Localidad localidad) {
	getSessionFactory().getCurrentSession().delete(localidad);
    }

    public Localidad findByNombreLocalidad(String nombreLocalidad) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Localidad where nombre=?")
		.setParameter(0, nombreLocalidad).list();
	return (Localidad) list.get(0);
    }

    public List<Localidad> findAll() {
	List<Localidad> localidades = new ArrayList<Localidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Localidad").list();
	for (Object object : list) {
	    localidades.add((Localidad) object);
	}
	return localidades;
    }
}
