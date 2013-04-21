package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;
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

    @Override
    public void save(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().save(publicidad);
    }

    @Override
    public void update(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().update(publicidad);
    }

    @Override
    public void delete(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().delete(publicidad);
    }

    @Override
    public List<Publicidad> findAll() {
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Publicidad").list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }

    @Override
    public List<Publicidad> findAllByEstadoVigente() {
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from Publicidad WHERE estado.id=2 and now()>=fechaDesde and"
				+ " now()<=fechaHasta ORDER BY fechaDesde ASC").list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }
    
    @Override
    public List<Publicidad> findByEstado(EstadoPublicidad estado){
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from Publicidad WHERE estado=? and"
				+ " now()<=fechaHasta ORDER BY fechaDesde ASC").setParameter(0, estado).list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }
}
