package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.Estadia;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.IEstadiaService;

@Service("estadiaService")
@Transactional
public class EstadiaService implements IEstadiaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Estadia estadia) {
	Session session = sessionFactory.getCurrentSession();
	session.save(estadia);
    }

    @Override
    public void update(Estadia estadia) {
	Session session = sessionFactory.getCurrentSession();
	session.update(estadia);
    }

    @Override
    public void delete(Estadia estadia) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(estadia);
    }

    @Override
    public List<Estadia> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Estadia> colores = new ArrayList<Estadia>();
	List<?> list = session.createQuery("from Estadia").list();
	for (Object object : list) {
	    colores.add((Estadia) object);
	}
	return colores;
    }

    @Override
    public Estadia findByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Estadia where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    return (Estadia) list.get(0);
	}
	return null;
    }
}