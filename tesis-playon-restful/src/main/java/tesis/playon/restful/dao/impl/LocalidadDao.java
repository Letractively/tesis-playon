package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ILocalidadDao;
import tesis.playon.restful.domain.Barrio;
import tesis.playon.restful.domain.Localidad;

@Repository("localidadDao")
public class LocalidadDao extends HibernateDaoSupport implements ILocalidadDao {

    @Override
    public void save(Localidad localidad) {
	getSessionFactory().getCurrentSession().save(localidad);
    }

    @Override
    public void update(Localidad localidad) {
	getSessionFactory().getCurrentSession().update(localidad);
    }

    @Override
    public void delete(Localidad localidad) {
	getSessionFactory().getCurrentSession().delete(localidad);
    }

    @Override
    public Localidad findByNombreLocalidad(String nombreLocalidad) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Localidad where nombre=?")
		.setParameter(0, nombreLocalidad).list();
	return (Localidad) list.get(0);
    }

    @Override
    public List<Localidad> findAll() {
	List<Localidad> localidades = new ArrayList<Localidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Localidad order by nombre").list();
	for (Object object : list) {
	    localidades.add((Localidad) object);
	}
	return localidades;
    }
    
    @Override
    public Set<Barrio> findBarrio(Localidad localidad){
	Set<Barrio> barrios = new HashSet<Barrio>(0);
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Barrio where localidad=?")
		.setParameter(0, localidad).list();
	for (Object object : list) {
	    barrios.add((Barrio) object);
	}
	return barrios;
    }
    
}