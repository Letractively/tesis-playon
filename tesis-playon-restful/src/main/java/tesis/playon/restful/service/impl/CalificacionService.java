package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.Calificacion;
import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.ICalificacionService;

@Service("calificacionService")
@Transactional
public class CalificacionService implements ICalificacionService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Calificacion calificacion) {
	Session session = sessionFactory.getCurrentSession();
	session.save(calificacion);
    }

    @Override
    public void update(Calificacion calificacion) {
	Session session = sessionFactory.getCurrentSession();
	session.update(calificacion);
    }

    @Override
    public void delete(Calificacion calificacion) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(calificacion);
    }

    @Override
    public List<Calificacion> findByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	List<?> list = session.createQuery("from Calificacion where playa=?")
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
	Session session = sessionFactory.getCurrentSession();
	List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	List<?> list = session.createQuery("from Calificacion").list();
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
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Calificacion where playa=? and cliente=?").setParameter(0, playa)
		.setParameter(1, cliente).list();
	if (!list.isEmpty()) {
	    return true;
	}
	return false;
    }
}