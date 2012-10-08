package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Barrio;
import tesis.playon.restful.domain.Localidad;
import tesis.playon.restful.service.ILocalidadService;

@Service("localidadService")
public class LocalidadService implements ILocalidadService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Localidad localidad) {
	Session session = sessionFactory.getCurrentSession();
	session.save(localidad);
    }

    @Override
    public void update(Localidad localidad) {
	Session session = sessionFactory.getCurrentSession();
	session.update(localidad);
    }

    @Override
    public void delete(Localidad localidad) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(localidad);
    }

    @Override
    public Localidad findByNombreLocalidad(String nombreLocalidad) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Localidad where nombre=?")
		.setParameter(0, nombreLocalidad).list();
	return (Localidad) list.get(0);
    }

    @Override
    public List<Localidad> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Localidad> localidades = new ArrayList<Localidad>();
	List<?> list = session.createQuery("from Localidad order by nombre").list();
	for (Object object : list) {
	    localidades.add((Localidad) object);
	}
	return localidades;
    }
    
    @Override
    public Set<Barrio> findBarrio(Localidad localidad){
	Session session = sessionFactory.getCurrentSession();
	Set<Barrio> barrios = new HashSet<Barrio>(0);
	List<?> list = session.createQuery("from Barrio where localidad=?")
		.setParameter(0, localidad).list();
	for (Object object : list) {
	    barrios.add((Barrio) object);
	}
	return barrios;
    }
}