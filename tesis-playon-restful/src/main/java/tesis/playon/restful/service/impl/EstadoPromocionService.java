package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.EstadoPromocion;
import tesis.playon.restful.service.IEstadoPromocionService;

@Service("estadoPromocionService")
public class EstadoPromocionService implements IEstadoPromocionService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(EstadoPromocion estadoPromocion) {
	Session session = sessionFactory.getCurrentSession();
	session.save(estadoPromocion);
    }

    @Override
    public void update(EstadoPromocion estadoPromocion) {
	Session session = sessionFactory.getCurrentSession();
	session.update(estadoPromocion);
    }

    @Override
    public void delete(EstadoPromocion estadoPromocion) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(estadoPromocion);
    }

    @Override
    public EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from EstadoPromocion where nombre=?")
		.setParameter(0, nombreEstadoPromocion).list();
	return (EstadoPromocion) list.get(0);
    }

    @Override
    public List<EstadoPromocion> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<EstadoPromocion> listaEstadoPromocion = new ArrayList<EstadoPromocion>();
	List<?> list = session.createQuery("from EstadoPromocion").list();
	for (Object obj : list) {
	    listaEstadoPromocion.add((EstadoPromocion) obj);
	}
	return listaEstadoPromocion;
    }
}