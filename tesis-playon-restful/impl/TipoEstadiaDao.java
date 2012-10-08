package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITipoEstadiaDao;
import tesis.playon.web.model.TipoEstadia;

/**
 * 
 * @author garribere
 * 
 */
public class TipoEstadiaDao implements ITipoEstadiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(TipoEstadia tipoEstadia) {
	session.save(tipoEstadia);
    }

    public void update(TipoEstadia tipoEstadia) {
	session.update(tipoEstadia);
    }

    public void delete(TipoEstadia tipoEstadia) {
	session.delete(tipoEstadia);
    }

    public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
	List<?> list = session.createQuery("from TipoEstadia where nombre=?")
		.setParameter(0, nombreTipoEstadia).list();
	return (TipoEstadia) list.get(0);
    }

    public List<TipoEstadia> findAll() {
	List<TipoEstadia> tipos = new ArrayList<TipoEstadia>();
	List<?> list = session.createQuery("from TipoEstadia order by nombre").list();
	for (Object object : list) {
	    tipos.add((TipoEstadia) object);
	}
	return tipos;
    }

}
