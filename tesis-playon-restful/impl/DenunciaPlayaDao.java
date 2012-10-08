package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IDenunciaPlayaDao;
import tesis.playon.web.model.DenunciaPlaya;

/**
 * @author garribere
 * 
 */
public class DenunciaPlayaDao implements IDenunciaPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(DenunciaPlaya denunciaPlaya) {
	session.save(denunciaPlaya);
    }

    public void update(DenunciaPlaya denunciaPlaya) {
	session.update(denunciaPlaya);
    }

    public void delete(DenunciaPlaya denunciaPlaya) {
	session.delete(denunciaPlaya);
    }

    public DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya) {
	List<?> list = session.createQuery("from DenunciaPlaya where asunto=?")
		.setParameter(0, asuntoDenunciaPlaya).list();
	return (DenunciaPlaya) list.get(0);
    }

    public List<DenunciaPlaya> findAll() {
	List<DenunciaPlaya> denuncias = new ArrayList<DenunciaPlaya>();
	List<?> list = session.createQuery("from DenunciaPlaya").list();
	for (Object object : list) {
	    denuncias.add((DenunciaPlaya) object);
	}
	return denuncias;
    }
}
