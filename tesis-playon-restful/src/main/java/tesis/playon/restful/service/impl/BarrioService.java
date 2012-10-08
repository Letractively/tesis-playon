package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Barrio;
import tesis.playon.restful.service.IBarrioService;

@Service("barrioService")
public class BarrioService implements IBarrioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Barrio barrio) {
	Session session = sessionFactory.getCurrentSession();
	session.save(barrio);
    }

    @Override
    public void update(Barrio barrio) {
	Session session = sessionFactory.getCurrentSession();
	session.update(barrio);
    }

    @Override
    public void delete(Barrio barrio) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(barrio);
    }

    @Override
    public Barrio findByNombreBarrio(String nombreBarrio) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Barrio where nombre=?")
		.setParameter(0, nombreBarrio).list();
	if (!list.isEmpty())
	    return (Barrio) list.get(0);
	return null;
    }

    @Override
    public List<Barrio> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Barrio> barrios = new ArrayList<Barrio>();
	List<?> list = session.createQuery("from Barrio order by nombre").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		barrios.add((Barrio) object);
	    }
	    return barrios;
	}
	return null;
    }

}