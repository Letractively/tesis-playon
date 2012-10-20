package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITransaccionPlayaDao;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.TransaccionPlaya;

/**
 * 
 * @author alejandro
 * 
 */
public class TransaccionPlayaDao implements ITransaccionPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().save(transaccionPlaya);
    }

    public void update(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().update(transaccionPlaya);
    }

    public void delete(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().delete(transaccionPlaya);
    }

    public List<TransaccionPlaya> findAll() {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TransaccionPlaya").list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    public TransaccionPlaya findByTransaccionPlayaID(int transaccionPlayaID) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where transaccionPlayaID=?").setParameter(0, transaccionPlayaID)
		.list();
	return (TransaccionPlaya) list.get(0);
    }

    @Override
    public List<TransaccionPlaya> findTransaccionesNoLiquidadas() {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where liquidacion is null").list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    @Override
    public List<TransaccionPlaya> findByCuentaPlaya(CuentaPlaya cuentaPlaya) {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where cuentaPlaya = ?").setParameter(0, cuentaPlaya).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    @Override
    public List<TransaccionPlaya> findNoLiquidadasByCuentaPlaya(CuentaPlaya cuentaPlaya) {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where cuentaPlaya = ? and liquidacion is null")
		.setParameter(0, cuentaPlaya).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    public List<TransaccionPlaya> findTransaccionesByCuentaPlaya(CuentaPlaya cuentaPlaya) {

	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where cuentaPlaya = ?").setParameter(0, cuentaPlaya).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;

    }

}
