package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IEstadoPublicidadDao;
import tesis.playon.restful.domain.EstadoPublicidad;

@Repository("estadoPublicidadDao")
public class EstadoPublicidadDao extends HibernateDaoSupport implements IEstadoPublicidadDao {

    @Override
    public void save(EstadoPublicidad estadoPublicidad) {
	getSessionFactory().getCurrentSession().save(estadoPublicidad);
    }

    @Override
    public void update(EstadoPublicidad estadoPublicidad) {
	getSessionFactory().getCurrentSession().update(estadoPublicidad);
    }

    @Override
    public void delete(EstadoPublicidad estadoPublicidad) {
	getSessionFactory().getCurrentSession().delete(estadoPublicidad);
    }

    @Override
    public EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPublicidad where nombre=?")
		.setParameter(0, nombreEstadoPublicidad).list();
	return (EstadoPublicidad) list.get(0);
    }

    @Override
    public List<EstadoPublicidad> findAll() {
	List<EstadoPublicidad> listaEstadoPublicidad = new ArrayList<EstadoPublicidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPublicidad").list();
	for (Object obj : list) {
	    listaEstadoPublicidad.add((EstadoPublicidad) obj);
	}
	return listaEstadoPublicidad;
    }

}