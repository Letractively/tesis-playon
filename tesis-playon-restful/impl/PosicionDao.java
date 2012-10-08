package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tesis.playon.web.dao.IPosicionDao;
import tesis.playon.web.model.Posicion;

/**
 * 
 * @author gmorales
 * 
 */
@Repository("posicionDao")
public class PosicionDao implements IPosicionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Posicion posicion) {
	session.save(posicion);
    }

    public void update(Posicion posicion) {
	session.update(posicion);
    }

    public void delete(Posicion posicion) {
	session.delete(posicion);
    }

    public Posicion findByUbicacion(String ubicacion) {
	List<?> list = session.createQuery("from Posicion where ubicacion=?")
		.setParameter(0, ubicacion).list();
	return (Posicion) list.get(0);
    }

    public List<Posicion> findAll() {
	List<Posicion> posiciones = new ArrayList<Posicion>();
	List<?> list = session.createQuery("from Posicion").list();
	for (Object object : list) {
	    posiciones.add((Posicion) object);
	}
	return posiciones;
    }

}
