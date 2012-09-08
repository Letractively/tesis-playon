package tesis.playon.web.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ICalificacionDao;
import tesis.playon.web.model.Calificacion;
import tesis.playon.web.model.Playa;

public class CalificacionDao implements ICalificacionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(Calificacion calificacion) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void update(Calificacion calificacion) {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Calificacion calificacion) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Calificacion> findByPlaya(Playa playa) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Calificacion> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

}
