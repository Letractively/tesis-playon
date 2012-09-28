package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ICuentaPlayaDao;
import tesis.playon.restful.domain.CuentaPlaya;
import tesis.playon.restful.domain.Playa;

@Repository("cuentaPlayaDao")
public class CuentaPlayaDao extends HibernateDaoSupport implements ICuentaPlayaDao {

    @Override
    public void save(CuentaPlaya cuentaPlaya) {
	getSessionFactory().getCurrentSession().save(cuentaPlaya);
    }

    @Override
    public void update(CuentaPlaya cuentaPlaya) {
	getSessionFactory().getCurrentSession().update(cuentaPlaya);
    }

    @Override
    public void delete(CuentaPlaya cuentaPlaya) {
	getSessionFactory().getCurrentSession().delete(cuentaPlaya);
    }

    @Override
    public List<CuentaPlaya> findAll() {
	List<CuentaPlaya> cuentas = new ArrayList<CuentaPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaPlaya").list();
	for (Object object : list) {
	    cuentas.add((CuentaPlaya) object);
	}
	return cuentas;
    }

    @Override
    public CuentaPlaya findByNroCuenta(Integer nroCuenta) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaPlaya where nroCuenta=?")
		.setParameter(0, nroCuenta).list();
	if (!list.isEmpty()) {
	    return (CuentaPlaya) list.get(0);
	}
	return null;
    }

    @Override
    public CuentaPlaya findByPlaya(Playa playa) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaPlaya where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    return (CuentaPlaya) list.get(0);
	}
	return null;
    }
    
}