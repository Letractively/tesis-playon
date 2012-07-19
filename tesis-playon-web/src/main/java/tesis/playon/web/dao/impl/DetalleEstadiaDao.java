package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tesis.playon.web.dao.IDetalleEstadiaDao;
import tesis.playon.web.model.DetalleEstadia;

@Repository("detalleEstadiaDao")
public class DetalleEstadiaDao implements IDetalleEstadiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().save(detalleEstadia);
    }

    public void update(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().update(detalleEstadia);
    }

    public void delete(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().delete(detalleEstadia);
    }

    public List<DetalleEstadia> findAll() {
	List<DetalleEstadia> detalleEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia").list();
	for (Object object : list) {
	    detalleEstadia.add((DetalleEstadia) object);
	}
	return detalleEstadia;
    }
}
