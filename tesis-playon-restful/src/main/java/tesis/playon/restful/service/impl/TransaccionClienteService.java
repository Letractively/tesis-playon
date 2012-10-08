package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.TransaccionCliente;
import tesis.playon.restful.service.ITransaccionClienteService;

@Service("transaccionClienteService")
@Transactional
public class TransaccionClienteService implements ITransaccionClienteService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(TransaccionCliente transaccionCliente) {
	Session session = sessionFactory.getCurrentSession();
	session.save(transaccionCliente);
    }

    @Override
    public void update(TransaccionCliente transaccionCliente) {
	Session session = sessionFactory.getCurrentSession();
	session.update(transaccionCliente);
    }

    @Override
    public void delete(TransaccionCliente transaccionCliente) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(transaccionCliente);
    }

    @Override
    public TransaccionCliente findByCuentaCliente(String cuentaClienteID) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session
		.createQuery("from TransaccionCliente where cuentaCliente=?").setParameter(0, cuentaClienteID).list();
	return (TransaccionCliente) list.get(0);
    }

    @Override
    public List<TransaccionCliente> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<TransaccionCliente> transaccionesCliente = new ArrayList<TransaccionCliente>();
	List<?> list = session.createQuery("from TransaccionCliente").list();
	for (Object object : list) {
	    transaccionesCliente.add((TransaccionCliente) object);
	}
	return transaccionesCliente;
    }
}