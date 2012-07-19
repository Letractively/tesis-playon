package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITransaccionClienteDao;
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

    public TransaccionCliente findByCuentaCliente(String cuentaClienteID) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionCliente where cuentaCliente=?").setParameter(0, cuentaClienteID).list();
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
}
