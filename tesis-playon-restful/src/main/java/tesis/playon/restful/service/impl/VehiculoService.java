package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Vehiculo;
import tesis.playon.restful.service.IVehiculoService;

@Service("vehiculoService")
public class VehiculoService implements IVehiculoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Vehiculo vehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(vehiculo);
    }

    @Override
    public void update(Vehiculo vehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.update(vehiculo);
    }

    @Override
    public void delete(Vehiculo vehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(vehiculo);
    }

    @Override
    public Vehiculo findByPatenteVehiculo(String patenteVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Vehiculo where patente=?")
		.setParameter(0, patenteVehiculo).list();
	if (!list.isEmpty()) {
	    return (Vehiculo) list.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Vehiculo> findByCliente(int idCliente) {
	Session session = sessionFactory.getCurrentSession();
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

    @Override
    public List<Vehiculo> findAll() {
	Session session = sessionFactory.getCurrentSession();
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
}