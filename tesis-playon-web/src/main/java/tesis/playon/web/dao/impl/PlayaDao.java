package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPlayaDao;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;

public class PlayaDao implements IPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Playa playa) {
	getSessionFactory().getCurrentSession().save(playa);
    }

    public void update(Playa playa) {
	getSessionFactory().getCurrentSession().update(playa);
    }

    public void delete(Playa playa) {
	getSessionFactory().getCurrentSession().delete(playa);
    }

    public Playa findByNombreComercial(String nombreComercial) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where nombreComercial=?  order by razonSocial")
		.setParameter(0, nombreComercial).list();
	return (Playa) list.get(0);
    }

    public Playa findByRazonSocial(String razonSocial) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where razonSocial=?  order by razonSocial").setParameter(0, razonSocial)
		.list();
	return (Playa) list.get(0);
    }

    public List<Playa> findPlayasCercanas(Double longitud, Double latitud, int distancia) {
	// Query query = getSessionFactory().getCurrentSession()
	// .createSQLQuery("CALL busquedaplaya(:platitud, :plongitud, :pdistancia")
	// .setParameter("platitud", latitud).setParameter("plongitud", longitud)
	// .setParameter("pdistancia", distancia);
	Query query = getSessionFactory().getCurrentSession().getNamedQuery("callPlayasStoreProcedure")
		.setParameter("platitud", latitud).setParameter("plongitud", longitud)
		.setParameter("pdistancia", distancia);
	List<?> list = query.list();
	List<Playa> playas = new ArrayList<Playa>();

	for (Object object : list) {
	    playas.add((Playa) object);
	}
	return playas;
    }

    public List<Playa> findAll() {
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa order by razonSocial").list();
	for (Object object : list) {
	    playa.add((Playa) object);
	}
	return playa;
    }

    public List<Playa> findByEstado(EstadoPlaya estado) {
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa where estado=?")
		.setParameter(0, estado).list();
	for (Object object : list) {
	    playa.add((Playa) object);
	}
	return playa;
    }
}