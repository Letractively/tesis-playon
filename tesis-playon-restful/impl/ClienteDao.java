package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IClienteDao;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Usuario;

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
	session.save(cliente);
    }

    public void update(Cliente cliente) {
	session.update(cliente);
    }

    public void delete(Cliente cliente) {
	session.delete(cliente);
    }

    public Cliente findByNumeroCliente(Integer numeroCliente) {
	List<?> list = session.createQuery("from Cliente where nroCliente=?")
		.setParameter(0, numeroCliente).list();
	return (Cliente) list.get(0);
    }

    public Cliente findByNombreUsuario(String nombreUser) {
	List<?> list = session.createQuery("from Usuario where nombreUser=?")
		.setParameter(0, nombreUser).list();
	Usuario usuario = (Usuario) list.get(0);
	List<?> usuarioList = session.createQuery("from Cliente where usuario=?")
		.setParameter(0, usuario).list();
	return (Cliente) usuarioList.get(0);
    }

    public List<Cliente> findAll() {
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<?> list = session.createQuery("from Cliente").list();
	for (Object object : list) {
	    clientes.add((Cliente) object);
	}
	return clientes;
    }

    public Cliente findByIdUsuario(Usuario usuario) {
	List<?> list = session.createQuery("from Cliente where usuario=?")
		.setParameter(0, usuario).list();
	if(!list.isEmpty())
	    return (Cliente) list.get(0);
	return null;
    }

}
