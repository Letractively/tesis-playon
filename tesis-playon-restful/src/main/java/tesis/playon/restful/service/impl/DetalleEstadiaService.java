package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.DetalleEstadia;
import tesis.playon.restful.domain.Vehiculo;
import tesis.playon.restful.service.IDetalleEstadiaService;

@Service("detalleEstadiaService")
public class DetalleEstadiaService implements IDetalleEstadiaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(DetalleEstadia detalleEstadia) {
	Session session = sessionFactory.getCurrentSession();
	session.save(detalleEstadia);
    }

    @Override
    public void update(DetalleEstadia detalleEstadia) {
	Session session = sessionFactory.getCurrentSession();
	session.update(detalleEstadia);
    }

    @Override
    public void delete(DetalleEstadia detalleEstadia) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(detalleEstadia);
    }

    @Override
    public List<DetalleEstadia> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<DetalleEstadia> detalleEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = session.createQuery("from DetalleEstadia").list();
	for (Object object : list) {
	    detalleEstadia.add((DetalleEstadia) object);
	}
	return detalleEstadia;
    }

    @Override
    public DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from DetalleEstadia where vehiculo=? and cobrado=0")
		.setParameter(0, vehiculo).list();
	if (!list.isEmpty()) {
	    return (DetalleEstadia) list.get(0);
	}
	return null;
    }
}