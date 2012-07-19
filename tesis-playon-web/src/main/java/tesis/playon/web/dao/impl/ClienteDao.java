package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IClienteDao;
import tesis.playon.web.model.Cliente;

/**
 * @author Pablo
 * 
 */
public class ClienteDao implements IClienteDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Cliente cliente) {
	getSessionFactory().getCurrentSession().save(cliente);
    }

    public void update(Cliente cliente) {
	getSessionFactory().getCurrentSession().update(cliente);
    }

    public void delete(Cliente cliente) {
	getSessionFactory().getCurrentSession().delete(cliente);
    }

    public Cliente findByNumeroCliente(Integer numeroCliente) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Cliente where nroCliente=?")
		.setParameter(0, numeroCliente).list();
	return (Cliente) list.get(0);
    }

    public List<Cliente> findAll() {
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Cliente").list();
	for (Object object : list) {
	    clientes.add((Cliente) object);
	}
	return clientes;
    }
}
