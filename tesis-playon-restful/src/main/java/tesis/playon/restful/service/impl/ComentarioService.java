package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.Comentario;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.IComentarioService;

@Service("comentarioService")
@Transactional
public class ComentarioService implements IComentarioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Comentario comentario) {
	Session session = sessionFactory.getCurrentSession();
	session.save(comentario);
    }

    @Override
    public void update(Comentario comentario) {
	Session session = sessionFactory.getCurrentSession();
	session.update(comentario);
    }

    @Override
    public void delete(Comentario comentario) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(comentario);
    }

    @Override
    public List<Comentario> findByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	List<Comentario> comentarios = new ArrayList<Comentario>();
	List<?> list = session
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
	Session session = sessionFactory.getCurrentSession();
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