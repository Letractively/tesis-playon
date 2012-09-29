package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.IComentarioDao;
import tesis.playon.restful.domain.Comentario;
import tesis.playon.restful.domain.Playa;

public class ComentarioDao extends HibernateDaoSupport implements IComentarioDao {

    @Override
    public void save(Comentario comentario) {
	getSessionFactory().getCurrentSession().save(comentario);
    }

    @Override
    public void update(Comentario comentario) {
	getSessionFactory().getCurrentSession().update(comentario);
    }

    @Override
    public void delete(Comentario comentario) {
	getSessionFactory().getCurrentSession().delete(comentario);
    }

    @Override
    public List<Comentario> findByPlaya(Playa playa) {
	List<Comentario> comentarios = new ArrayList<Comentario>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Comentario where playa=? and habilitado=1").setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		comentarios.add((Comentario) object);
	    }
	    return comentarios;
	}
	return null;
    }

    @Override
    public List<Comentario> findAll() {
	List<Comentario> comentarios = new ArrayList<Comentario>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Comentario").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		comentarios.add((Comentario) object);
	    }
	    return comentarios;
	}
	return null;
    }

}