package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IComentarioDao;
import tesis.playon.web.model.Comentario;
import tesis.playon.web.model.Playa;

/**
 * 
 * @author garribere
 * 
 */
public class ComentarioDao implements IComentarioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Comentario comentario) {
	session.save(comentario);
    }

    public void update(Comentario comentario) {
	session.update(comentario);
    }

    public void delete(Comentario comentario) {
	session.delete(comentario);
    }

    public List<Comentario> findByPlaya(Playa playa) {
	List<Comentario> comentarios = new ArrayList<Comentario>();
	List<?> list = session.createQuery("from Comentario where playa=? and habilitado=1")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		comentarios.add((Comentario) object);
	    }
	    return comentarios;
	}
	return null;
    }

    public List<Comentario> findAll() {
	List<Comentario> comentarios = new ArrayList<Comentario>();
	List<?> list = session.createQuery("from Comentario").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		comentarios.add((Comentario) object);
	    }
	    return comentarios;
	}
	return null;
    }

}
