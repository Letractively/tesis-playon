package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * 
 * @author gmorales
 * 
 */
public class PaisDao implements IPaisDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }
    
    public void save(Pais pais) {
	getSessionFactory().getCurrentSession().save(pais);
    }

    public void update(Pais pais) {
	getSessionFactory().getCurrentSession().update(pais);
    }

    public void delete(Pais pais) {
	getSessionFactory().getCurrentSession().delete(pais);
    }

    public Pais findByNombrePais(String nombrePais) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais where nombre=?")
		.setParameter(0, nombrePais).list();
	return (Pais) list.get(0);
    }

    public List<Pais> findAll() {
	List<Pais> paises = new ArrayList<Pais>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais").list();
	for (Object object : list) {
	    paises.add((Pais) object);
	}
	return paises;
    }
    
    public Set<Provincia> findProvincia(Pais pais){
	Set<Provincia> provincias = new HashSet<Provincia>(0);
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Localidad where provincia=?")
		.setParameter(0, pais).list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }
}
