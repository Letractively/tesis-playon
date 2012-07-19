package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ICuentaPlayaDao;
import tesis.playon.web.model.CuentaPlaya;

/**
 * 
 * @author garribere
 * 
 */
public class CuentaPlayaDao implements ICuentaPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(CuentaPlaya cuentaPlaya) {
	getSessionFactory().getCurrentSession().save(cuentaPlaya);
    }

    public void update(CuentaPlaya cuentaPlaya) {
	getSessionFactory().getCurrentSession().update(cuentaPlaya);
    }

    public void delete(CuentaPlaya cuentaPlaya) {
	getSessionFactory().getCurrentSession().delete(cuentaPlaya);
    }

    public CuentaPlaya findByNroCuenta(Integer nroCuenta) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaPlaya where nroCuenta=?")
		.setParameter(0, nroCuenta).list();
	return (CuentaPlaya) list.get(0);
    }

    public List<CuentaPlaya> findAll() {
	List<CuentaPlaya> cuentas = new ArrayList<CuentaPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaPlaya").list();
	for (Object object : list) {
	    cuentas.add((CuentaPlaya) object);
	}
	return cuentas;
    }
}
