package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.ITransaccionClienteDao;
import tesis.playon.restful.domain.TransaccionCliente;

public class TransaccionClienteDao extends HibernateDaoSupport implements ITransaccionClienteDao {

    @Override
    public void save(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().save(transaccionCliente);
    }

    @Override
    public void update(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().update(transaccionCliente);
    }

    @Override
    public void delete(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().delete(transaccionCliente);
    }

    @Override
    public TransaccionCliente findByCuentaCliente(String cuentaClienteID) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionCliente where cuentaCliente=?").setParameter(0, cuentaClienteID).list();
	return (TransaccionCliente) list.get(0);
    }

    @Override
    public List<TransaccionCliente> findAll() {
	List<TransaccionCliente> transaccionesCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TransaccionCliente").list();
	for (Object object : list) {
	    transaccionesCliente.add((TransaccionCliente) object);
	}
	return transaccionesCliente;
    }

}