package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IEstadoPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;

@Repository("estadoPublicidadDao")
public class EstadoPublicidadDao extends CustomHibernateDaoSupport implements IEstadoPublicidadDao {

    @Override
    public void save(EstadoPublicidad estadoPublicidad) {
	getHibernateTemplate().save(estadoPublicidad);
    }

    @Override
    public void update(EstadoPublicidad estadoPublicidad) {
	getHibernateTemplate().update(estadoPublicidad);
    }

    @Override
    public void delete(EstadoPublicidad estadoPublicidad) {
	getHibernateTemplate().delete(estadoPublicidad);
    }

    @Override
    public EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad) {
	List<?> list = getHibernateTemplate().find("from EstadoPublicidad where nombre=?", nombreEstadoPublicidad);
	return (EstadoPublicidad) list.get(0);
    }
    
}
