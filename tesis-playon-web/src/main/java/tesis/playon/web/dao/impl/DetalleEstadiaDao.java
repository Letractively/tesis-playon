package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tesis.playon.web.dao.IDetalleEstadiaDao;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Vehiculo;

@Repository("detalleEstadiaDao")
public class DetalleEstadiaDao implements IDetalleEstadiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().save(detalleEstadia);
    }

    public void update(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().update(detalleEstadia);
    }

    public void delete(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().delete(detalleEstadia);
    }

    public List<DetalleEstadia> findAll() {
	List<DetalleEstadia> detalleEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detalleEstadia.add((DetalleEstadia) object);
	    }
	    return detalleEstadia;
	}
	return null;
    }

    public List<DetalleEstadia> findByEstadia(Estadia estadia) {
	List<DetalleEstadia> detallesEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia where estadia=?")
		.setParameter(0, estadia).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detallesEstadia.add((DetalleEstadia) object);
	    }
	    return detallesEstadia;
	}
	return null;
    }

    public List<DetalleEstadia> findBy(Estadia estadia) {
	List<DetalleEstadia> detallesEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia where estadia=?")
		.setParameter(0, estadia).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detallesEstadia.add((DetalleEstadia) object);
	    }
	    return detallesEstadia;
	}
	return null;
    }

    public List<DetalleEstadia> findByHorarios(Estadia estadia, Date horaInicio, Date horaFin) {
	List<DetalleEstadia> detallesEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from DetalleEstadia where estadia=? and fechaHoraIngreso>=? and fechaHoraEgreso<=?")
		.setParameter(0, estadia).setParameter(1, horaInicio).setParameter(2, horaFin).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detallesEstadia.add((DetalleEstadia) object);
	    }
	    return detallesEstadia;
	}
	return null;

    }

    @Override
    public DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from DetalleEstadia where vehiculo=? and cobrado=0").setParameter(0, vehiculo).list();
	if (!list.isEmpty()) {
	    return (DetalleEstadia) list.get(0);
	}
	return null;
    }

}
