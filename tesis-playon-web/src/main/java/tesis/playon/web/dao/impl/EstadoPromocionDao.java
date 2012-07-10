package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IEstadoPromocionDao;
import tesis.playon.web.model.EstadoPromocion;

/**
 * 
 * @author garribere
 * 
 */
@Repository("estadoPromocionDao")
public class EstadoPromocionDao extends CustomHibernateDaoSupport implements IEstadoPromocionDao {

    public void save(EstadoPromocion estadoPromocion) {
	getHibernateTemplate().save(estadoPromocion);
    }

    public void update(EstadoPromocion estadoPromocion) {
	getHibernateTemplate().update(estadoPromocion);
    }

    public void delete(EstadoPromocion estadoPromocion) {
	getHibernateTemplate().delete(estadoPromocion);
    }

    public EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion) {
	List<?> list = getHibernateTemplate().find("from EstadoPromocion where nombre=?", nombreEstadoPromocion);
	return (EstadoPromocion) list.get(0);
    }

    public List<EstadoPromocion> findAll() {
	List<EstadoPromocion> listaEstadoPromocion = new ArrayList<EstadoPromocion>();
	List<?> list = getHibernateTemplate().find("from EstadoPromocion");
	for (Object obj : list) {
	    listaEstadoPromocion.add((EstadoPromocion) obj);
	}
	return listaEstadoPromocion;
    }

}
