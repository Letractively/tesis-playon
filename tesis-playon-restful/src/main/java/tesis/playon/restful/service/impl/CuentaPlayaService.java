package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.CuentaPlaya;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.ICuentaPlayaService;

@Service("cuentaPlayaService")
public class CuentaPlayaService implements ICuentaPlayaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(CuentaPlaya cuentaPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.save(cuentaPlaya);
    }

    @Override
    public void update(CuentaPlaya cuentaPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.update(cuentaPlaya);
    }

    @Override
    public void delete(CuentaPlaya cuentaPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(cuentaPlaya);
    }

    @Override
    public List<CuentaPlaya> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<CuentaPlaya> cuentas = new ArrayList<CuentaPlaya>();
	List<?> list = session.createQuery("from CuentaPlaya").list();
	for (Object object : list) {
	    cuentas.add((CuentaPlaya) object);
	}
	return cuentas;
    }

    @Override
    public CuentaPlaya findByNroCuenta(Integer nroCuenta) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from CuentaPlaya where nroCuenta=?")
		.setParameter(0, nroCuenta).list();
	if (!list.isEmpty()) {
	    return (CuentaPlaya) list.get(0);
	}
	return null;
    }

    @Override
    public CuentaPlaya findByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from CuentaPlaya where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    return (CuentaPlaya) list.get(0);
	}
	return null;
    }
}