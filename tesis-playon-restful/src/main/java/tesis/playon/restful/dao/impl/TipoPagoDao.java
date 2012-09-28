package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ITipoPagoDao;
import tesis.playon.restful.domain.TipoPago;

@Repository("tipoPagoDao")
public class TipoPagoDao extends HibernateDaoSupport implements ITipoPagoDao {

    @Override
    public void save(TipoPago tipoPago) {
	getSessionFactory().getCurrentSession().save(tipoPago);
    }

    @Override
    public void update(TipoPago tipoPago) {
	getSessionFactory().getCurrentSession().update(tipoPago);
    }

    @Override
    public void delete(TipoPago tipoPago) {
	getSessionFactory().getCurrentSession().delete(tipoPago);
    }

    @Override
    public TipoPago findByNameTipoPago(String nombreTipoPago) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoPago where nombre=?")
		.setParameter(0, nombreTipoPago).list();
	return (TipoPago) list.get(0);
    }

    @Override
    public List<TipoPago> findAll() {
	List<TipoPago> tipos = new ArrayList<TipoPago>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoPago order by nombre").list();
	for (Object object : list) {
	    tipos.add((TipoPago) object);
	}
	return tipos;
    }
    
}