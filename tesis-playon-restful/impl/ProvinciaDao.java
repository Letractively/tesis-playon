package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IProvinciaDao;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * 
 * @author gmorales
 * 
 */
public class ProvinciaDao implements IProvinciaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Provincia provincia) {
	session.save(provincia);
    }

    public void update(Provincia cliente) {
	session.update(cliente);
    }

    public void delete(Provincia cliente) {
	session.delete(cliente);
    }

    public Provincia findByNombreProvincia(String nombreProvincia) {
	List<?> list = session.createQuery("from Provincia where nombre=?")
		.setParameter(0, nombreProvincia).list();
	return (Provincia) list.get(0);
    }

    public List<Provincia> findAll() {
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = session.createQuery("from Provincia order by nombre").list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }
    
    public Provincia findByProvinciaId(Integer id){
	List<?> list = session.createQuery("from Provincia where id=?")
		.setParameter(0, id).list();
	return (Provincia) list.get(0);
    }
    
    public List<Provincia> findProvincias(Pais pais){
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = session.createQuery("from Provincia where pais=?")
		.setParameter(0, pais).list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }
}