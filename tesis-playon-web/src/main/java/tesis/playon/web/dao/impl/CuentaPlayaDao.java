package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.ICuentaPlayaDao;
import tesis.playon.web.model.CuentaPlaya;

/**
 * 
 * @author garribere
 *
 */
@Repository("cuentaPlayaDao")
public class CuentaPlayaDao extends CustomHibernateDaoSupport implements ICuentaPlayaDao {

    public void save(CuentaPlaya cuentaPlaya) {
	getHibernateTemplate().save(cuentaPlaya);
    }

    public void update(CuentaPlaya cuentaPlaya) {
	getHibernateTemplate().update(cuentaPlaya);
    }

    public void delete(CuentaPlaya cuentaPlaya) {
	getHibernateTemplate().delete(cuentaPlaya);
    }

    public CuentaPlaya findByNroCuenta(Integer nroCuenta) {
	List<?> list = getHibernateTemplate().find("from CuentaPlaya where nroCuenta=?", nroCuenta);
	return (CuentaPlaya) list.get(0);
    }
    
    public List<CuentaPlaya> findAll(){
	List<CuentaPlaya> cuentas = new ArrayList<CuentaPlaya>();
	List<?> list = getHibernateTemplate().find("from CuentaPlaya");
	for (Object object : list) {
	    cuentas.add((CuentaPlaya)object);
	}
	return cuentas;
    }
}
