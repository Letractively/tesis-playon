package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ICuentaClienteDao;
import tesis.playon.restful.domain.CuentaCliente;

@Repository("cuentaClienteDao")
public class CuentaClienteDao extends HibernateDaoSupport implements ICuentaClienteDao {

    @Override
    public void save(CuentaCliente cuentaCliente) {
	getSessionFactory().getCurrentSession().save(cuentaCliente);
    }

    @Override
    public void update(CuentaCliente cuentaCliente) {
	getSessionFactory().getCurrentSession().update(cuentaCliente);
    }

    @Override
    public void delete(CuentaCliente cuentaCliente) {
	getSessionFactory().getCurrentSession().delete(cuentaCliente);
    }

    @Override
    public List<CuentaCliente> findAll() {
	List<CuentaCliente> colores = new ArrayList<CuentaCliente>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaCliente").list();
	for (Object object : list) {
	    colores.add((CuentaCliente) object);
	}
	return colores;
    }

    @Override
    public CuentaCliente findByNroCuenta(Integer nroCuenta) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaCliente where nroCuenta=?")
		.setParameter(0, nroCuenta).list();
	if (!list.isEmpty()) {
	    return (CuentaCliente) list.get(0);
	}
	return null;
    }

}