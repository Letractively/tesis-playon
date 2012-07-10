package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IBarrioDao;
import tesis.playon.web.model.Barrio;

/**
 * 
 * @author gmorales
 *
 */
@Repository("barrioDao")
public class BarrioDao extends CustomHibernateDaoSupport implements IBarrioDao {

    public void save(Barrio barrio) {
	getHibernateTemplate().save(barrio);
    }

    public void update(Barrio barrio) {
	getHibernateTemplate().update(barrio);
    }

    public void delete(Barrio barrio) {
	getHibernateTemplate().delete(barrio);
    }

    public Barrio findByNombreBarrio(String nombreBarrio) {
	List<?> list = getHibernateTemplate().find("from Barrio where nombre=?", nombreBarrio);
	return (Barrio) list.get(0);
    }
    
    public List<Barrio> findAll(){
	List<Barrio> barrios = new ArrayList<Barrio>();
	List<?> list = getHibernateTemplate().find("from Barrio");
	for (Object object : list) {
	    barrios.add((Barrio)object);
	}
	return barrios;
    }
}
