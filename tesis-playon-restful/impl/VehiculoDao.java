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
	session.save(vehiculo);
    }

    public void update(Vehiculo vehiculo) {
	session.update(vehiculo);
    }

    public void delete(Vehiculo vehiculo) {
	session.delete(vehiculo);
    }

    public Vehiculo findByPatenteVehiculo(String patenteVehiculo) {
	List<?> list = session.createQuery("from Vehiculo where patente=?")
		.setParameter(0, patenteVehiculo).list();
	if (!list.isEmpty()) {
	    return (Vehiculo) list.get(0);
	} else {
	    return null;
	}
    }

    public List<Vehiculo> findByCliente(int idCliente) {
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	List<?> list = session
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

    public List<Vehiculo> findAll() {
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	List<?> list = session.createQuery("from Vehiculo").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		vehiculos.add((Vehiculo) object);
	    }
	    return vehiculos;
	}
	return null;

    }

    // public List<Vehiculo> findByEstado() {
    // List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    // List<?> list =
    // session.createQuery("from Vehiculo where habilitado=true order by patente").list();
    // for (Object object : list) {
    // vehiculos.add((Vehiculo) object);
    // }
    // return vehiculos;
    // }

}
