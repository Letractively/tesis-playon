package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ITarifaDao;
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

    public Tarifa findByNombreTarifa(String nombreTarifa) {
	List<?> list = getHibernateTemplate().find("from Tarifa where nombre=?", nombreTarifa);
	return (Tarifa) list.get(0);
    }

}