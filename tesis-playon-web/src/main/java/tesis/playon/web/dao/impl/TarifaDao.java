package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.ITarifaDao;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;

/**
 * 
 * @author gmorales
 * 
 */
@Repository("tarifaDao")
public class TarifaDao extends CustomHibernateDaoSupport implements ITarifaDao {

    public void save(Tarifa tarifa) {
	getHibernateTemplate().save(tarifa);
    }

    public void update(Tarifa tarifa) {
	getHibernateTemplate().update(tarifa);
    }

    public void delete(Tarifa tarifa) {
	getHibernateTemplate().delete(tarifa);
    }

    public Tarifa findByPlayaID(Playa playa) {
	List<?> list = getHibernateTemplate().find("from Tarifa where playa=?", playa);
	return (Tarifa) list.get(0);
    }

    public List<Tarifa> findAll(){
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getHibernateTemplate().find("from Tarifa");
	for (Object object : list) {
	    tarifas.add((Tarifa)object);
	}
	return tarifas;
    }
}