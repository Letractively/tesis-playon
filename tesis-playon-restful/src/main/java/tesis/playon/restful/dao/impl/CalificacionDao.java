package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.ICalificacionDao;
import tesis.playon.restful.domain.Calificacion;
import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Playa;

public class CalificacionDao extends HibernateDaoSupport implements ICalificacionDao {

    @Override
    public void save(Calificacion calificacion) {
	getSessionFactory().getCurrentSession().save(calificacion);
    }

    @Override
    public void update(Calificacion calificacion) {
	getSessionFactory().getCurrentSession().update(calificacion);
    }

    @Override
    public void delete(Calificacion calificacion) {
	getSessionFactory().getCurrentSession().delete(calificacion);
    }

    @Override
    public List<Calificacion> findByPlaya(Playa playa) {
	List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Calificacion where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		calificaciones.add((Calificacion) obj);
	    }
	    return calificaciones;
	}
	return null;
    }

    @Override
    public List<Calificacion> findAll() {
	List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Calificacion").list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		calificaciones.add((Calificacion) obj);
	    }
	    return calificaciones;
	}
	return null;
    }

    @Override
    public boolean isRate(Playa playa, Cliente cliente) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Calificacion where playa=? and cliente=?").setParameter(0, playa)
		.setParameter(1, cliente).list();
	if (!list.isEmpty()) {
	    return true;
	}
	return false;
    }

}