/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IClienteDao;
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

    public Cliente findByNumeroCliente(String numeroCliente) {
	List<?> list = getHibernateTemplate().find("from Cliente where nroCliente=?", numeroCliente);
	return (Cliente) list.get(0);
    }

}
