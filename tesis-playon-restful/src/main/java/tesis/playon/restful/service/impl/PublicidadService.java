package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Publicidad;
import tesis.playon.restful.service.IPublicidadService;

@Service("publicidadService")
public class PublicidadService implements IPublicidadService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Publicidad publicidad) {
	Session session = sessionFactory.getCurrentSession();
	session.save(publicidad);
    }

    @Override
    public void update(Publicidad publicidad) {
	Session session = sessionFactory.getCurrentSession();
	session.update(publicidad);
    }

    @Override
    public void delete(Publicidad publicidad) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(publicidad);
    }

    @Override
    public List<Publicidad> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = session.createQuery("from Publicidad").list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }
}