package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPublicidadDao;
import tesis.playon.web.model.Publicidad;

/**
 * Clase DAO de Publicidad
 * 
 * @author alejandro
 * @date 07/07/2012
 */
public class PublicidadDao implements IPublicidadDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Publicidad publicidad) {
	session.save(publicidad);
    }

    public void update(Publicidad publicidad) {
	session.update(publicidad);
    }

    public void delete(Publicidad publicidad) {
	session.delete(publicidad);
    }

    public List<Publicidad> findAll() {
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = session.createQuery("from Publicidad").list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }
}
