package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IBarrioDao;
import tesis.playon.restful.domain.Barrio;

@Repository("barrioDao")
public class BarrioDao extends HibernateDaoSupport implements IBarrioDao {

    @Override
    public void save(Barrio barrio) {
	getSessionFactory().getCurrentSession().save(barrio);
    }

    @Override
    public void update(Barrio barrio) {
	getSessionFactory().getCurrentSession().update(barrio);
    }

    @Override
    public void delete(Barrio barrio) {
	getSessionFactory().getCurrentSession().delete(barrio);
    }

    @Override
    public Barrio findByNombreBarrio(String nombreBarrio) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Barrio where nombre=?")
		.setParameter(0, nombreBarrio).list();
	if (!list.isEmpty())
	    return (Barrio) list.get(0);
	return null;
    }

    @Override
    public List<Barrio> findAll() {
	List<Barrio> barrios = new ArrayList<Barrio>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Barrio order by nombre").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		barrios.add((Barrio) object);
	    }
	    return barrios;
	}
	return null;
    }

}