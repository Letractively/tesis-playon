package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITransaccionClienteDao;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TransaccionCliente;

/**
 * @author Pablo
 * 
 */
public class TransaccionClienteDao implements ITransaccionClienteDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().save(transaccionCliente);
    }

    public void update(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().update(transaccionCliente);
    }

    public void delete(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().delete(transaccionCliente);
    }

    public TransaccionCliente findByCuentaCliente(CuentaCliente cuentaCliente) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionCliente where cuentaCliente=?").setParameter(0, cuentaCliente).list();
	return (TransaccionCliente) list.get(0);
    }

    public List<TransaccionCliente> findAll() {
	List<TransaccionCliente> transaccionesCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TransaccionCliente").list();
	for (Object object : list) {
	    transaccionesCliente.add((TransaccionCliente) object);
	}
	return transaccionesCliente;
    }

    public List<TransaccionCliente> findTransaccionesByCuentaCliente(CuentaCliente cuentaCliente) {
	List<TransaccionCliente> transaccionesCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionCliente where cuentaCliente=?").setParameter(0, cuentaCliente).list();
	for (Object object : list) {
	    transaccionesCliente.add((TransaccionCliente) object);
	}
	return transaccionesCliente;
    }

    public List<TransaccionCliente> findTransaccionesByFecha(CuentaCliente cuentaCliente, Date fechaD, Date fechaH) {
	List<TransaccionCliente> transaccionCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from TransaccionCliente as tp where "
				+ "tp.cuentaCliente=? and tp.fecha>=? and tp.fecha<=? order by tp.fecha")
		.setParameter(0, cuentaCliente).setParameter(1, fechaD).setParameter(2, fechaH).list();

	if (!list.isEmpty()) {
	    for (Object object : list) {
		transaccionCliente.add(((TransaccionCliente) object));
	    }
	    return transaccionCliente;
	}
	return null;
    }

}
