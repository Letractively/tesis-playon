package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ITipoPagoDao;
import tesis.playon.web.model.TipoPago;

@Repository("tipoPagoDao")
public class TipoPagoDao extends CustomHibernateDaoSupport implements ITipoPagoDao {

    @Override
    public void save(TipoPago tipoPago) {
	getHibernateTemplate().save(tipoPago);
    }

    @Override
    public void update(TipoPago tipoPago) {
	getHibernateTemplate().update(tipoPago);
    }

    @Override
    public void delete(TipoPago tipoPago) {
	getHibernateTemplate().delete(tipoPago);
    }

    @Override
    public TipoPago findByNameTipoPago(String nombreTipoPago) {
	List<?> list = getHibernateTemplate().find("from TipoPago where nombre=?", nombreTipoPago);
	return (TipoPago) list.get(0);
    }

}
