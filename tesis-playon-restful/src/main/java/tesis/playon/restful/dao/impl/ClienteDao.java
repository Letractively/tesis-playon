package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IClienteDao;
import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Usuario;

@Repository("clienteDao")
public class ClienteDao extends HibernateDaoSupport implements IClienteDao {

    @Override
    public void save(Cliente cliente) {
	getSessionFactory().getCurrentSession().save(cliente);
    }

    @Override
    public void update(Cliente cliente) {
	getSessionFactory().getCurrentSession().update(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
	getSessionFactory().getCurrentSession().delete(cliente);
    }

    @Override
    public Cliente findByNumeroCliente(Integer numeroCliente) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Cliente where nroCliente=?")
		.setParameter(0, numeroCliente).list();
	return (Cliente) list.get(0);
    }

    @Override
    public Cliente findByNombreUsuario(String nombreUser) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Usuario where nombreUser=?")
		.setParameter(0, nombreUser).list();
	Usuario usuario = (Usuario) list.get(0);
	List<?> usuarioList = getSessionFactory().getCurrentSession().createQuery("from Cliente where usuario=?")
		.setParameter(0, usuario).list();
	return (Cliente) usuarioList.get(0);
    }

    @Override
    public List<Cliente> findAll() {
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Cliente").list();
	for (Object object : list) {
	    clientes.add((Cliente) object);
	}
	return clientes;
    }

    @Override
    public Cliente findByIdUsuario(Usuario usuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Cliente where usuario=?")
		.setParameter(0, usuario).list();
	if (!list.isEmpty())
	    return (Cliente) list.get(0);
	return null;
    }

}