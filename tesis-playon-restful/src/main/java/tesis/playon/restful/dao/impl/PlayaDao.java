package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.IPlayaDao;
import tesis.playon.restful.domain.EstadoPlaya;
import tesis.playon.restful.domain.Playa;

public class PlayaDao extends HibernateDaoSupport implements IPlayaDao {

    @Override
    public void save(Playa playa) {
	getSessionFactory().getCurrentSession().save(playa);
    }

    @Override
    public void update(Playa playa) {
	getSessionFactory().getCurrentSession().update(playa);
    }

    @Override
    public void delete(Playa playa) {
	getSessionFactory().getCurrentSession().delete(playa);
    }

    @Override
    public Playa findByNombreComercial(String nombreComercial) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where nombreComercial=?  order by razonSocial")
		.setParameter(0, nombreComercial).list();
	return (Playa) list.get(0);
    }

    @Override
    public Playa findByRazonSocial(String razonSocial) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where razonSocial=?  order by razonSocial").setParameter(0, razonSocial)
		.list();
	if (!list.isEmpty())
	    return (Playa) list.get(0);
	return null;
    }

    @Override
    public List<Playa> findPlayasCercanas(Double longitud, Double latitud, int distancia) {
	Query query = getSessionFactory().getCurrentSession().getNamedQuery("callPlayasStoreProcedure")
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
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa order by razonSocial").list();
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
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa where estado=?")
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