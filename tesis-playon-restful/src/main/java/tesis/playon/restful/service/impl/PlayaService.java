package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.EstadoPlaya;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.IPlayaService;

@Service("playaService")
public class PlayaService implements IPlayaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	session.save(playa);
    }

    @Override
    public void update(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	session.update(playa);
    }

    @Override
    public void delete(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(playa);
    }

    @Override
    public Playa findByNombreComercial(String nombreComercial) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session
		.createQuery("from Playa where nombreComercial=?  order by razonSocial")
		.setParameter(0, nombreComercial).list();
	return (Playa) list.get(0);
    }

    @Override
    public Playa findByRazonSocial(String razonSocial) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session
		.createQuery("from Playa where razonSocial=?  order by razonSocial").setParameter(0, razonSocial)
		.list();
	if (!list.isEmpty())
	    return (Playa) list.get(0);
	return null;
    }

    @Override
    public List<Playa> findPlayasCercanas(Double longitud, Double latitud, int distancia) {
	Session session = sessionFactory.getCurrentSession();
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

    @Override
    public List<Playa> findAll() {
	Session session = sessionFactory.getCurrentSession();
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

    @Override
    public List<Playa> findByEstado(EstadoPlaya estado) {
	Session session = sessionFactory.getCurrentSession();
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