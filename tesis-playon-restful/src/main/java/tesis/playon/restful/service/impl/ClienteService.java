package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.service.IClienteService;

@Service("clienteService")
public class ClienteService implements IClienteService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Cliente cliente) {
	Session session = sessionFactory.getCurrentSession();
	session.save(cliente);
    }

    @Override
    public void update(Cliente cliente) {
	Session session = sessionFactory.getCurrentSession();
	session.update(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(cliente);
    }

    @Override
    public Cliente findByNumeroCliente(Integer numeroCliente) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Cliente where nroCliente=?")
		.setParameter(0, numeroCliente).list();
	return (Cliente) list.get(0);
    }

    @Override
    public Cliente findByNombreUsuario(String nombreUser) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Usuario where nombreUser=?")
		.setParameter(0, nombreUser).list();
	Usuario usuario = (Usuario) list.get(0);
	List<?> usuarioList = session.createQuery("from Cliente where usuario=?")
		.setParameter(0, usuario).list();
	return (Cliente) usuarioList.get(0);
    }

    @Override
    public List<Cliente> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<?> list = session.createQuery("from Cliente").list();
	for (Object object : list) {
	    clientes.add((Cliente) object);
	}
	return clientes;
    }

    @Override
    public Cliente findByIdUsuario(Usuario usuario) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Cliente where usuario=?")
		.setParameter(0, usuario).list();
	if (!list.isEmpty())
	    return (Cliente) list.get(0);
	return null;
    }

}