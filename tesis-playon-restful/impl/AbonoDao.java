package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IAbonoDao;
import tesis.playon.web.model.Abono;

/**
 * 
 * @author gmorales
 * 
 */
public class AbonoDao implements IAbonoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Abono abono) {
	getSessionFactory().getCurrentSession().save(abono);
    }

    public void update(Abono abono) {
	getSessionFactory().getCurrentSession().update(abono);
    }

    public void delete(Abono abono) {
	getSessionFactory().getCurrentSession().delete(abono);
    }

    public List<Abono> findAll() {
	List<Abono> abonos = new ArrayList<Abono>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Abono").list();
	if(!list.isEmpty()){
	    for (Object obj : list) {
		    abonos.add((Abono) obj);
		}
		return abonos;
	}
	return null;
    }

}
