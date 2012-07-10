package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.ITipoPagoDao;
import tesis.playon.web.model.TipoPago;

@Repository("tipoPagoDao")
public class TipoPagoDao extends CustomHibernateDaoSupport implements ITipoPagoDao {

    public void save(TipoPago tipoPago) {
	getHibernateTemplate().save(tipoPago);
    }

    public void update(TipoPago tipoPago) {
	getHibernateTemplate().update(tipoPago);
    }

    public void delete(TipoPago tipoPago) {
	getHibernateTemplate().delete(tipoPago);
    }

    public TipoPago findByNameTipoPago(String nombreTipoPago) {
	List<?> list = getHibernateTemplate().find("from TipoPago where nombre=?", nombreTipoPago);
	return (TipoPago) list.get(0);
    }
    
    public List<TipoPago> findAll(){
	List<TipoPago> tipos = new ArrayList<TipoPago>();
	List<?> list = getHibernateTemplate().find("from TipoPago");
	for (Object object : list) {
	    tipos.add((TipoPago)object);
	}
	return tipos;
    }
}
