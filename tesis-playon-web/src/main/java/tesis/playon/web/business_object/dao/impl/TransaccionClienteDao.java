/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ITransaccionClienteDao;
import tesis.playon.web.model.TransaccionCliente;

/**
 * @author Pablo
 *
 */
@Repository("transaccionClienteDao")
public class TransaccionClienteDao extends CustomHibernateDaoSupport implements ITransaccionClienteDao{
    
    public void save(TransaccionCliente transaccionCliente) {
	getHibernateTemplate().save(transaccionCliente);
    }

    public void update(TransaccionCliente transaccionCliente) {
	getHibernateTemplate().update(transaccionCliente);
    }

    public void delete(TransaccionCliente transaccionCliente) {
	getHibernateTemplate().delete(transaccionCliente);
    }

    public TransaccionCliente findByCuentaCliente(String cuentaClienteID) {
	List<?> list = getHibernateTemplate().find("from TransaccionCliente where cuentaCliente=?", cuentaClienteID);
	return (TransaccionCliente) list.get(0);
    }
    
    public List<TransaccionCliente> findAll(){
	List<TransaccionCliente> transaccionesCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getHibernateTemplate().find("from TransaccionCliente");
	for (Object object : list) {
	    transaccionesCliente.add((TransaccionCliente)object);
	}
	return transaccionesCliente;
    }
}
