package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IEstadoDenunciaDao;
import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author Pablo
 * 
 */
public class EstadoDenunciaDao implements IEstadoDenunciaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(EstadoDenuncia estadoDenuncia) {
	session.save(estadoDenuncia);
    }

    public void update(EstadoDenuncia estadoDenuncia) {
	session.update(estadoDenuncia);
    }

    public void delete(EstadoDenuncia estadoDenuncia) {
	session.delete(estadoDenuncia);
    }

    public EstadoDenuncia findByNombreEstadoDenuncia(String nombreDenuncia) {
	List<?> list = session.createQuery("from EstadoDenuncia where nombre=?")
		.setParameter(0, nombreDenuncia).list();
	return (EstadoDenuncia) list.get(0);
    }

    public List<EstadoDenuncia> findAll() {
	List<EstadoDenuncia> estados = new ArrayList<EstadoDenuncia>();
	List<?> list = session.createQuery("from EstadoDenuncia").list();
	for (Object object : list) {
	    estados.add((EstadoDenuncia) object);
	}
	return estados;
    }
}
