package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tesis.playon.web.dao.IDetalleEstadiaDao;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Vehiculo;

@Repository("detalleEstadiaDao")
public class DetalleEstadiaDao implements IDetalleEstadiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(DetalleEstadia detalleEstadia) {
	session.save(detalleEstadia);
    }

    public void update(DetalleEstadia detalleEstadia) {
	session.update(detalleEstadia);
    }

    public void delete(DetalleEstadia detalleEstadia) {
	session.delete(detalleEstadia);
    }

    public List<DetalleEstadia> findAll() {
	List<DetalleEstadia> detalleEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = session.createQuery("from DetalleEstadia").list();
	for (Object object : list) {
	    detalleEstadia.add((DetalleEstadia) object);
	}
	return detalleEstadia;
    }

    @Override
    public DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo) {
	List<?> list = session.createQuery("from DetalleEstadia where vehiculo=? and cobrado=0")
		.setParameter(0, vehiculo).list();
	if (!list.isEmpty()) {
	    return (DetalleEstadia) list.get(0);
	}
	return null;
    }

}
