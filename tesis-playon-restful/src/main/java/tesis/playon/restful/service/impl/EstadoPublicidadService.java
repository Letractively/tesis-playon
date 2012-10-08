package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.EstadoPublicidad;
import tesis.playon.restful.service.IEstadoPublicidadService;

@Service("estadoPublicidadService")
@Transactional
public class EstadoPublicidadService implements IEstadoPublicidadService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(EstadoPublicidad estadoPublicidad) {
	Session session = sessionFactory.getCurrentSession();
	session.save(estadoPublicidad);
    }

    @Override
    public void update(EstadoPublicidad estadoPublicidad) {
	Session session = sessionFactory.getCurrentSession();
	session.update(estadoPublicidad);
    }

    @Override
    public void delete(EstadoPublicidad estadoPublicidad) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(estadoPublicidad);
    }

    @Override
    public EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from EstadoPublicidad where nombre=?")
		.setParameter(0, nombreEstadoPublicidad).list();
	return (EstadoPublicidad) list.get(0);
    }

    @Override
    public List<EstadoPublicidad> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<EstadoPublicidad> listaEstadoPublicidad = new ArrayList<EstadoPublicidad>();
	List<?> list = session.createQuery("from EstadoPublicidad").list();
	for (Object obj : list) {
	    listaEstadoPublicidad.add((EstadoPublicidad) obj);
	}
	return listaEstadoPublicidad;
    }
}