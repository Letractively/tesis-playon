package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IEstadiaDao;
import tesis.playon.restful.domain.Estadia;
import tesis.playon.restful.domain.Playa;

@Repository("estadiaDao")
public class EstadiaDao extends HibernateDaoSupport implements IEstadiaDao {

    @Override
    public void save(Estadia estadia) {
	getSessionFactory().getCurrentSession().save(estadia);
    }

    @Override
    public void update(Estadia estadia) {
	getSessionFactory().getCurrentSession().update(estadia);
    }

    @Override
    public void delete(Estadia estadia) {
	getSessionFactory().getCurrentSession().delete(estadia);
    }

    @Override
    public List<Estadia> findAll() {
	List<Estadia> colores = new ArrayList<Estadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Estadia").list();
	for (Object object : list) {
	    colores.add((Estadia) object);
	}
	return colores;
    }

    @Override
    public Estadia findByPlaya(Playa playa) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Estadia where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    return (Estadia) list.get(0);
	}
	return null;
    }

}