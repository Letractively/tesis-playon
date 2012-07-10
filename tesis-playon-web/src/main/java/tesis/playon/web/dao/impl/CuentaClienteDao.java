package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.ICuentaClienteDao;
import tesis.playon.web.model.CuentaCliente;

@Repository("cuentaClienteDao")
public class CuentaClienteDao extends CustomHibernateDaoSupport implements ICuentaClienteDao {

    public void save(CuentaCliente cuentaCliente) {
	getHibernateTemplate().save(cuentaCliente);
    }

    public void update(CuentaCliente cuentaCliente) {
	getHibernateTemplate().update(cuentaCliente);
    }

    public void delete(CuentaCliente cuentaCliente) {
	getHibernateTemplate().delete(cuentaCliente);
    }

    public CuentaCliente findByNroCuenta(String nroCuenta) {
	List<?> list = getHibernateTemplate().find("from CuentaCliente where nroCuenta=?", nroCuenta);
	return (CuentaCliente) list.get(0);
    }
    
    public List<CuentaCliente> findAll() {
	List<CuentaCliente> colores = new ArrayList<CuentaCliente>();
	List<?> list = getHibernateTemplate().find("from CuentaCliente");
	for (Object object : list) {
	    colores.add((CuentaCliente) object);
	}
	return colores;
    }

}
