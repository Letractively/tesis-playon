package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IBarrioDao;
import tesis.playon.web.model.Barrio;

/**
 * 
 * @author gmorales
 * 
 */
public class BarrioDao implements IBarrioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Barrio barrio) {
	session.save(barrio);
    }

    public void update(Barrio barrio) {
	session.update(barrio);
    }

    public void delete(Barrio barrio) {
	session.delete(barrio);
    }

    public Barrio findByNombreBarrio(String nombreBarrio) {
	List<?> list = session.createQuery("from Barrio where nombre=?")
		.setParameter(0, nombreBarrio).list();
	if (!list.isEmpty())
	    return (Barrio) list.get(0);
	return null;
    }

    public List<Barrio> findAll() {
	List<Barrio> barrios = new ArrayList<Barrio>();
	List<?> list = session.createQuery("from Barrio order by nombre").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		barrios.add((Barrio) object);
	    }
	    return barrios;
	}
	return null;
    }

}
