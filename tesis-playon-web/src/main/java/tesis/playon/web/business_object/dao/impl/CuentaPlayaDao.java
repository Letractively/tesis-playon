package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ICuentaPlaya;
import tesis.playon.web.model.CuentaPlaya;

/**
 * 
 * @author garribere
 *
 */
@Repository("cuentaPlayaDao")
public class CuentaPlayaDao extends CustomHibernateDaoSupport implements ICuentaPlaya {

    public void save(CuentaPlaya cuentaPlaya) {
	getHibernateTemplate().save(cuentaPlaya);
    }

    public void update(CuentaPlaya cuentaPlaya) {
	getHibernateTemplate().update(cuentaPlaya);
    }

    public void delete(CuentaPlaya cuentaPlaya) {
	getHibernateTemplate().delete(cuentaPlaya);
    }

    public CuentaPlaya findByNroCuenta(String nroCuenta) {
	List<?> list = getHibernateTemplate().find("from CuentaPlaya where nroCuenta=?", nroCuenta);
	return (CuentaPlaya) list.get(0);
    }
}
