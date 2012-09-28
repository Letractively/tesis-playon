package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ILiquidacionDao;
import tesis.playon.restful.domain.Liquidacion;

@Repository("liquidacionDao")
public class LiquidacionDao extends HibernateDaoSupport implements ILiquidacionDao {

    @Override
    public void save(Liquidacion liquidacion) {
	getSessionFactory().getCurrentSession().save(liquidacion);
    }

    @Override
    public void update(Liquidacion liquidacion) {
	getSessionFactory().getCurrentSession().update(liquidacion);
    }

    @Override
    public void delete(Liquidacion liquidacion) {
	getSessionFactory().getCurrentSession().delete(liquidacion);
    }

    @Override
    public List<Liquidacion> findAll() {
	List<Liquidacion> colores = new ArrayList<Liquidacion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Liquidacion").list();
	for (Object object : list) {
	    colores.add((Liquidacion) object);
	}
	return colores;
    }

}