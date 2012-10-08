package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.Posicion;
import tesis.playon.restful.service.IPosicionService;

@Service("posicionService")
@Transactional
public class PosicionService implements IPosicionService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Posicion posicion) {
	Session session = sessionFactory.getCurrentSession();
	session.save(posicion);
    }

    @Override
    public void update(Posicion posicion) {
	Session session = sessionFactory.getCurrentSession();
	session.update(posicion);
    }

    @Override
    public void delete(Posicion posicion) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(posicion);
    }

    @Override
    public Posicion findByUbicacion(String ubicacion) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Posicion where ubicacion=?")
		.setParameter(0, ubicacion).list();
	return (Posicion) list.get(0);
    }

    @Override
    public List<Posicion> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Posicion> posiciones = new ArrayList<Posicion>();
	List<?> list = session.createQuery("from Posicion").list();
	for (Object object : list) {
	    posiciones.add((Posicion) object);
	}
	return posiciones;
    }
}