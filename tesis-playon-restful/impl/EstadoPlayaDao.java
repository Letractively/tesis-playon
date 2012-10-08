package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IEstadoPlayaDao;
import tesis.playon.web.model.EstadoPlaya;

public class EstadoPlayaDao implements IEstadoPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(EstadoPlaya estadoPlaya) {
	session.save(estadoPlaya);
    }

    public void update(EstadoPlaya estadoPlaya) {
	session.update(estadoPlaya);
    }

    public void delete(EstadoPlaya estadoPlaya) {
	session.delete(estadoPlaya);
    }

    public EstadoPlaya findByNombreEstadoPlaya(String nombreEstado) {
	List<?> list = session.createQuery("from EstadoPlaya where nombre=?")
		.setParameter(0, nombreEstado).list();
	return (EstadoPlaya) list.get(0);
    }

    public List<EstadoPlaya> findAll() {
	List<EstadoPlaya> estadoPlaya = new ArrayList<EstadoPlaya>();
	List<?> list = session.createQuery("from EstadoPlaya order by nombre").list();
	for (Object object : list) {
	    estadoPlaya.add((EstadoPlaya) object);
	}
	return estadoPlaya;
    }

}
