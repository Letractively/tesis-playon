package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IVehiculoDao;
import tesis.playon.web.model.Vehiculo;

/**
 * @author Pablo
 * 
 */
public class VehiculoDao implements IVehiculoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().save(vehiculo);
    }

    public void update(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().update(vehiculo);
    }

    public void delete(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().delete(vehiculo);
    }

    public Vehiculo findByPatenteVehiculo(String patenteVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Vehiculo where patente=?")
		.setParameter(0, patenteVehiculo).list();
	return (Vehiculo) list.get(0);
    }

    public List<Vehiculo> findAll() {
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Vehiculo").list();
	for (Object object : list) {
	    vehiculos.add((Vehiculo) object);
	}
	return vehiculos;
    }
}
