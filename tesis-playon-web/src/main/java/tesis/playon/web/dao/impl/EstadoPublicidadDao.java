package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IEstadoPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;

@Repository("estadoPublicidadDao")
public class EstadoPublicidadDao extends CustomHibernateDaoSupport implements IEstadoPublicidadDao {

    public void save(EstadoPublicidad estadoPublicidad) {
	getHibernateTemplate().save(estadoPublicidad);
    }

    public void update(EstadoPublicidad estadoPublicidad) {
	getHibernateTemplate().update(estadoPublicidad);
    }

    public void delete(EstadoPublicidad estadoPublicidad) {
	getHibernateTemplate().delete(estadoPublicidad);
    }

    public EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad) {
	List<?> list = getHibernateTemplate().find("from EstadoPublicidad where nombre=?", nombreEstadoPublicidad);
	return (EstadoPublicidad) list.get(0);
    }

    public List<EstadoPublicidad> findAll() {
	List<EstadoPublicidad> listaEstadoPublicidad = new ArrayList<EstadoPublicidad>();
	List<?> list = getHibernateTemplate().find("from EstadoPublicidad");
	for (Object obj : list) {
	    listaEstadoPublicidad.add((EstadoPublicidad) obj);
	}
	return listaEstadoPublicidad;
    }

}
