package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.IVehiculoDao;
import tesis.playon.restful.domain.Vehiculo;

public class VehiculoDao extends HibernateDaoSupport implements IVehiculoDao {

    @Override
    public void save(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().save(vehiculo);
    }

    @Override
    public void update(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().update(vehiculo);
    }

    @Override
    public void delete(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().delete(vehiculo);
    }

    @Override
    public Vehiculo findByPatenteVehiculo(String patenteVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Vehiculo where patente=?")
		.setParameter(0, patenteVehiculo).list();
	if (!list.isEmpty()) {
	    return (Vehiculo) list.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Vehiculo> findByCliente(int idCliente) {
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Vehiculo where cliente.id=? and habilitado=true order by patente")
		.setParameter(0, idCliente).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		vehiculos.add((Vehiculo) object);
	    }
	    return vehiculos;
	}
	return null;
    }

    @Override
    public List<Vehiculo> findAll() {
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Vehiculo").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		vehiculos.add((Vehiculo) object);
	    }
	    return vehiculos;
	}
	return null;

    }

}