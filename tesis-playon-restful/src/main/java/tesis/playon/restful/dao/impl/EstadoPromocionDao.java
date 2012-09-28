package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IEstadoPromocionDao;
import tesis.playon.restful.domain.EstadoPromocion;

@Repository("estadoPromocionDao")
public class EstadoPromocionDao extends HibernateDaoSupport implements IEstadoPromocionDao {

    @Override
    public void save(EstadoPromocion estadoPromocion) {
	getSessionFactory().getCurrentSession().save(estadoPromocion);
    }

    @Override
    public void update(EstadoPromocion estadoPromocion) {
	getSessionFactory().getCurrentSession().update(estadoPromocion);
    }

    @Override
    public void delete(EstadoPromocion estadoPromocion) {
	getSessionFactory().getCurrentSession().delete(estadoPromocion);
    }

    @Override
    public EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPromocion where nombre=?")
		.setParameter(0, nombreEstadoPromocion).list();
	return (EstadoPromocion) list.get(0);
    }

    @Override
    public List<EstadoPromocion> findAll() {
	List<EstadoPromocion> listaEstadoPromocion = new ArrayList<EstadoPromocion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPromocion").list();
	for (Object obj : list) {
	    listaEstadoPromocion.add((EstadoPromocion) obj);
	}
	return listaEstadoPromocion;
    }

}