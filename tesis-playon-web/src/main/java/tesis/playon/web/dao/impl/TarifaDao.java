package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITarifaDao;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;

/**
 * 
 * @author gmorales
 * 
 */
public class TarifaDao implements ITarifaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Tarifa tarifa) {
	getSessionFactory().getCurrentSession().save(tarifa);
    }

    public void update(Tarifa tarifa) {
	getSessionFactory().getCurrentSession().update(tarifa);
    }

    public void delete(Tarifa tarifa) {
	getSessionFactory().getCurrentSession().delete(tarifa);
    }

    public Tarifa findByPlayaID(Playa playa) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Tarifa where playa=?")
		.setParameter(0, playa).list();
	return (Tarifa) list.get(0);
    }

    public List<Tarifa> findAll() {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Tarifa").list();
	for (Object object : list) {
	    tarifas.add((Tarifa) object);
	}
	return tarifas;
    }
}