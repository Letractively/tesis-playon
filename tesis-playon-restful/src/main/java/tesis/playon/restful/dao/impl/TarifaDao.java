package tesis.playon.restful.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ITarifaDao;
import tesis.playon.restful.domain.CategoriaVehiculo;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.Tarifa;

@Repository("tarifaDao")
public class TarifaDao extends HibernateDaoSupport implements ITarifaDao {

    @Override
    public void save(Tarifa tarifa) {
	getSessionFactory().getCurrentSession().save(tarifa);
    }

    @Override
    public void update(Tarifa tarifa) {
	getSessionFactory().getCurrentSession().update(tarifa);
    }

    @Override
    public void delete(Tarifa tarifa) {
	tarifa.setVigente(new Boolean(false));
	tarifa.setFechaBaja(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	getSessionFactory().getCurrentSession().update(tarifa);
    }

    @Override
    public int deleteTarifasPlaya(Playa playa) {
	Query query = getSessionFactory().getCurrentSession().createQuery(
		"update Tarifa set fechaBaja = :fechaBaja, vigente = 0 " + " where playa = :playa");
	query.setParameter("fechaBaja", new Timestamp(Calendar.getInstance().getTimeInMillis()));
	query.setParameter("playa", playa);
	int result = query.executeUpdate();
	return result;
    }

    @Override
    public List<Tarifa> findAll() {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Tarifa where fechaBaja is null")
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
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession()
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
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession()
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