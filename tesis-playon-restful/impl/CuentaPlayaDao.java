package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ICuentaPlayaDao;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Playa;

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
	session.save(cuentaPlaya);
    }

    public void update(CuentaPlaya cuentaPlaya) {
	session.update(cuentaPlaya);
    }

    public void delete(CuentaPlaya cuentaPlaya) {
	session.delete(cuentaPlaya);
    }

    public List<CuentaPlaya> findAll() {
	List<CuentaPlaya> cuentas = new ArrayList<CuentaPlaya>();
	List<?> list = session.createQuery("from CuentaPlaya").list();
	for (Object object : list) {
	    cuentas.add((CuentaPlaya) object);
	}
	return cuentas;
    }

    public CuentaPlaya findByNroCuenta(Integer nroCuenta) {
	List<?> list = session.createQuery("from CuentaPlaya where nroCuenta=?")
		.setParameter(0, nroCuenta).list();
	if (!list.isEmpty()) {
	    return (CuentaPlaya) list.get(0);
	}
	return null;
    }

    public CuentaPlaya findByPlaya(Playa playa) {
	List<?> list = session.createQuery("from CuentaPlaya where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    return (CuentaPlaya) list.get(0);
	}
	return null;
    }
}
