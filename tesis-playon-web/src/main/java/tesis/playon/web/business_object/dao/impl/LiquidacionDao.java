package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ILiquidacionDao;
import tesis.playon.web.model.Liquidacion;

@Repository("liquidacionDao")
public class LiquidacionDao extends CustomHibernateDaoSupport implements ILiquidacionDao {

    public void save(Liquidacion liquidacion) {
	getHibernateTemplate().save(liquidacion);
    }

    public void update(Liquidacion liquidacion) {
	getHibernateTemplate().update(liquidacion);
    }

    public void delete(Liquidacion liquidacion) {
	getHibernateTemplate().delete(liquidacion);
    }

    public List<Liquidacion> findAll() {
	List<Liquidacion> colores = new ArrayList<Liquidacion>();
	List<?> list = getHibernateTemplate().find("from Liquidacion");
	for (Object object : list) {
	    colores.add((Liquidacion) object);
	}
	return colores;
    }

}
