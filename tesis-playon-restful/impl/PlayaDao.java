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
	session.save(playa);
    }

    public void update(Playa playa) {
	session.update(playa);
    }

    public void delete(Playa playa) {
	session.delete(playa);
    }

    public Playa findByNombreComercial(String nombreComercial) {
	List<?> list = session
		.createQuery("from Playa where nombreComercial=?  order by razonSocial")
		.setParameter(0, nombreComercial).list();
	return (Playa) list.get(0);
    }

    public Playa findByRazonSocial(String razonSocial) {
	List<?> list = session
		.createQuery("from Playa where razonSocial=?  order by razonSocial").setParameter(0, razonSocial)
		.list();
	if (!list.isEmpty())
	    return (Playa) list.get(0);
	return null;
    }

    public List<Playa> findPlayasCercanas(Double longitud, Double latitud, int distancia) {
	// Query query = session
	// .createSQLQuery("CALL busquedaplaya(:platitud, :plongitud, :pdistancia")
	// .setParameter("platitud", latitud).setParameter("plongitud", longitud)
	// .setParameter("pdistancia", distancia);
	Query query = session.getNamedQuery("callPlayasStoreProcedure")
		.setParameter("platitud", latitud).setParameter("plongitud", longitud)
		.setParameter("pdistancia", distancia);
	List<?> list = query.list();
	List<Playa> playas = new ArrayList<Playa>();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playas.add((Playa) object);
	    }
	    return playas;
	}
	return null;
    }

    public List<Playa> findAll() {
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = session.createQuery("from Playa order by razonSocial").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playa.add((Playa) object);
	    }
	    return playa;
	}
	return null;
    }

    public List<Playa> findByEstado(EstadoPlaya estado) {
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = session.createQuery("from Playa where estado=?")
		.setParameter(0, estado).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playa.add((Playa) object);
	    }
	    return playa;
	}
	return null;
    }
}