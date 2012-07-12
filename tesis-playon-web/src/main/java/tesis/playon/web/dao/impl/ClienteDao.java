package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IClienteDao;
import tesis.playon.web.model.Cliente;

/**
 * @author Pablo
 *
 */
@Repository("clienteDao")
public class ClienteDao extends CustomHibernateDaoSupport implements IClienteDao{
    
    public void save(Cliente cliente) {
	getHibernateTemplate().save(cliente);
    }

    public void update(Cliente cliente) {
	getHibernateTemplate().update(cliente);
    }

    public void delete(Cliente cliente) {
	getHibernateTemplate().delete(cliente);
    }

    public Cliente findByNumeroCliente(Integer numeroCliente) {
	List<?> list = getHibernateTemplate().find("from Cliente where nroCliente=?", numeroCliente);
	return (Cliente) list.get(0);
    }

    public List<Cliente> findAll(){
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<?> list = getHibernateTemplate().find("from Cliente");
	for (Object object : list) {
	    clientes.add((Cliente)object);
	}
	return clientes;
    }
}
