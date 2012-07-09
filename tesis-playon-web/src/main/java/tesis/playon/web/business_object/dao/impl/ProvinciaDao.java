package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IProvinciaDao;
import tesis.playon.web.model.Provincia;

/**
 * 
 * @author gmorales
 *
 */
@Repository("provinciaDao")
public class ProvinciaDao extends CustomHibernateDaoSupport implements IProvinciaDao {

    public void save(Provincia provincia) {
	getHibernateTemplate().save(provincia);
    }

    public void update(Provincia cliente) {
	getHibernateTemplate().update(cliente);
    }

    public void delete(Provincia cliente) {
	getHibernateTemplate().delete(cliente);
    }

    public Provincia findByNombreProvincia(String nombreProvincia) {
	List<?> list = getHibernateTemplate().find("from Provincia where nombre=?", nombreProvincia);
	return (Provincia) list.get(0);
    }
    
    public List<Provincia> findAll(){
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = getHibernateTemplate().find("from Provincia");
	for (Object object : list) {
	    provincias.add((Provincia)object);
	}
	return provincias;
    }
    
}
