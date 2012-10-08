package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ILocalidadDao;
import tesis.playon.web.model.Barrio;
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
	session.save(localidad);
    }

    public void update(Localidad localidad) {
	session.update(localidad);
    }

    public void delete(Localidad localidad) {
	session.delete(localidad);
    }

    public Localidad findByNombreLocalidad(String nombreLocalidad) {
	List<?> list = session.createQuery("from Localidad where nombre=?")
		.setParameter(0, nombreLocalidad).list();
	return (Localidad) list.get(0);
    }

    public List<Localidad> findAll() {
	List<Localidad> localidades = new ArrayList<Localidad>();
	List<?> list = session.createQuery("from Localidad order by nombre").list();
	for (Object object : list) {
	    localidades.add((Localidad) object);
	}
	return localidades;
    }
    
    public Set<Barrio> findBarrio(Localidad localidad){
	Set<Barrio> barrios = new HashSet<Barrio>(0);
	List<?> list = session.createQuery("from Barrio where localidad=?")
		.setParameter(0, localidad).list();
	for (Object object : list) {
	    barrios.add((Barrio) object);
	}
	return barrios;
    }
}
