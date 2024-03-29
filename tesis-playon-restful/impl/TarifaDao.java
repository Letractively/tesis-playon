package tesis.playon.web.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
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
	session.save(tarifa);
    }

    public void update(Tarifa tarifa) {
	session.update(tarifa);
    }

    public void delete(Tarifa tarifa) {
	//session.delete(tarifa);
	tarifa.setVigente(new Boolean(false));
	tarifa.setFechaBaja(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	session.update(tarifa);
    }

    public int deleteTarifasPlaya(Playa playa) {
	Query query = session.createQuery(
		"update Tarifa set fechaBaja = :fechaBaja, vigente = 0 " + " where playa = :playa");
	query.setParameter("fechaBaja", new Timestamp(Calendar.getInstance().getTimeInMillis()));
	query.setParameter("playa", playa);
	int result = query.executeUpdate();
	return result;
    }

    public List<Tarifa> findAll() {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = session.createQuery("from Tarifa where fechaBaja is null").list();
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
	List<?> list = session
		.createQuery("from Tarifa where playa=? and vigente = '1'").setParameter(0, playa).list();
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
	List<?> list = session
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
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = session
		.createQuery("from Tarifa where playa=? and fechaBaja is null").setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }
}