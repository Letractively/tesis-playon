package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITarifaDao;
import tesis.playon.web.model.CategoriaVehiculo;
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

        public List<Tarifa> findAll() {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Tarifa").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }

    public List<Tarifa> findTarifaVigenteByPlaya(Playa playa) {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Tarifa where playa=? and vigente = 1")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }

    @Override
    public List<Tarifa> findTarifaVigenteByPlayaAndCategoriaVehiculo(Playa playa, CategoriaVehiculo categoriaVehiculo) {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Tarifa where playa=? and categoriaVehiculo=? and vigente = 1")
		.setParameter(0, playa).setParameter(1, categoriaVehiculo).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }

    @Override
    public List<Tarifa> findByPlaya(Playa playa) {
	// TODO Auto-generated method stub
	return null;
    }
}