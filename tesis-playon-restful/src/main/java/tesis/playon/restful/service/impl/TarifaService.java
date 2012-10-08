package tesis.playon.restful.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.CategoriaVehiculo;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.Tarifa;
import tesis.playon.restful.service.ITarifaService;

@Service("tarifaService")
public class TarifaService implements ITarifaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Tarifa tarifa) {
	Session session = sessionFactory.getCurrentSession();
	session.save(tarifa);
    }

    @Override
    public void update(Tarifa tarifa) {
	Session session = sessionFactory.getCurrentSession();
	session.update(tarifa);
    }

    @Override
    public void delete(Tarifa tarifa) {
	Session session = sessionFactory.getCurrentSession();
	tarifa.setVigente(new Boolean(false));
	tarifa.setFechaBaja(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	session.update(tarifa);
    }

    @Override
    public int deleteTarifasPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery(
		"update Tarifa set fechaBaja = :fechaBaja, vigente = 0 " + " where playa = :playa");
	query.setParameter("fechaBaja", new Timestamp(Calendar.getInstance().getTimeInMillis()));
	query.setParameter("playa", playa);
	int result = query.executeUpdate();
	return result;
    }

    @Override
    public List<Tarifa> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = session.createQuery("from Tarifa where fechaBaja is null")
		.list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }

    @Override
    public List<Tarifa> findTarifaVigenteByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
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
	Session session = sessionFactory.getCurrentSession();
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
	Session session = sessionFactory.getCurrentSession();
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