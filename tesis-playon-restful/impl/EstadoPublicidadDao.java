package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IEstadoPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;

public class EstadoPublicidadDao implements IEstadoPublicidadDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(EstadoPublicidad estadoPublicidad) {
	session.save(estadoPublicidad);
    }

    public void update(EstadoPublicidad estadoPublicidad) {
	session.update(estadoPublicidad);
    }

    public void delete(EstadoPublicidad estadoPublicidad) {
	session.delete(estadoPublicidad);
    }

    public EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad) {
	List<?> list = session.createQuery("from EstadoPublicidad where nombre=?")
		.setParameter(0, nombreEstadoPublicidad).list();
	return (EstadoPublicidad) list.get(0);
    }

    public List<EstadoPublicidad> findAll() {
	List<EstadoPublicidad> listaEstadoPublicidad = new ArrayList<EstadoPublicidad>();
	List<?> list = session.createQuery("from EstadoPublicidad").list();
	for (Object obj : list) {
	    listaEstadoPublicidad.add((EstadoPublicidad) obj);
	}
	return listaEstadoPublicidad;
    }

}
