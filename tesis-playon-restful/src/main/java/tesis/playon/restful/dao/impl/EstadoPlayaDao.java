package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IEstadoPlayaDao;
import tesis.playon.restful.domain.EstadoPlaya;

@Repository("estadoPlayaDao")
public class EstadoPlayaDao extends HibernateDaoSupport implements IEstadoPlayaDao {

    @Override
    public void save(EstadoPlaya estadoPlaya) {
	getSessionFactory().getCurrentSession().save(estadoPlaya);
    }

    @Override
    public void update(EstadoPlaya estadoPlaya) {
	getSessionFactory().getCurrentSession().update(estadoPlaya);
    }

    @Override
    public void delete(EstadoPlaya estadoPlaya) {
	getSessionFactory().getCurrentSession().delete(estadoPlaya);
    }

    @Override
    public EstadoPlaya findByNombreEstadoPlaya(String nombreEstado) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPlaya where nombre=?")
		.setParameter(0, nombreEstado).list();
	return (EstadoPlaya) list.get(0);
    }

    @Override
    public List<EstadoPlaya> findAll() {
	List<EstadoPlaya> estadoPlaya = new ArrayList<EstadoPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPlaya order by nombre").list();
	for (Object object : list) {
	    estadoPlaya.add((EstadoPlaya) object);
	}
	return estadoPlaya;
    }

}