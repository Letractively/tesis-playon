package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITransaccionPlayaDao;
import tesis.playon.web.model.TransaccionPlaya;

/**
 * 
 * @author alejandro
 * 
 */
public class TransaccionPlayaDao implements ITransaccionPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(TransaccionPlaya transaccionPlaya) {
	session.save(transaccionPlaya);
    }

    public void update(TransaccionPlaya transaccionPlaya) {
	session.update(transaccionPlaya);
    }

    public void delete(TransaccionPlaya transaccionPlaya) {
	session.delete(transaccionPlaya);
    }

    public List<TransaccionPlaya> findAll() {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = session.createQuery("from TransaccionPlaya").list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

}
