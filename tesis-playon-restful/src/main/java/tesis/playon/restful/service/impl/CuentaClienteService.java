package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.CuentaCliente;
import tesis.playon.restful.service.ICuentaClienteService;

@Service("cuentaClienteService")
@Transactional
public class CuentaClienteService implements ICuentaClienteService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(CuentaCliente cuentaCliente) {
	Session session = sessionFactory.getCurrentSession();
	session.save(cuentaCliente);
    }

    @Override
    public void update(CuentaCliente cuentaCliente) {
	Session session = sessionFactory.getCurrentSession();
	session.update(cuentaCliente);
    }

    @Override
    public void delete(CuentaCliente cuentaCliente) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(cuentaCliente);
    }

    @Override
    public List<CuentaCliente> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<CuentaCliente> colores = new ArrayList<CuentaCliente>();
	List<?> list = session.createQuery("from CuentaCliente").list();
	for (Object object : list) {
	    colores.add((CuentaCliente) object);
	}
	return colores;
    }

    @Override
    public CuentaCliente findByNroCuenta(Integer nroCuenta) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from CuentaCliente where nroCuenta=?").setParameter(0, nroCuenta).list();
	if (!list.isEmpty()) {
	    return (CuentaCliente) list.get(0);
	}
	return null;
    }

    @Override
    public CuentaCliente findByNroCliente(Cliente cliente) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from CuentaCliente where cliente.nroCliente=?")
		.setParameter(0, cliente.getNroCliente()).list();
	if (!list.isEmpty())
	    return (CuentaCliente) list.get(0);
	return null;
    }
}