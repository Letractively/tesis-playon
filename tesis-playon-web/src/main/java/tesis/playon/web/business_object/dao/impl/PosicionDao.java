package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IPosicionDao;
import tesis.playon.web.model.Posicion;

/**
 * 
 * @author gmorales
 * 
 */
@Repository("posicionDao")
public class PosicionDao extends CustomHibernateDaoSupport implements IPosicionDao {

    public void save(Posicion posicion) {
	getHibernateTemplate().save(posicion);
    }

    public void update(Posicion posicion) {
	getHibernateTemplate().update(posicion);
    }

    public void delete(Posicion posicion) {
	getHibernateTemplate().delete(posicion);
    }

    public Posicion findByUbicacion(String ubicacion) {
	List<?> list = getHibernateTemplate().find("from Posicion where ubicacion=?", ubicacion);
	return (Posicion) list.get(0);
    }
    
    public List<Posicion> findAll(){
	List<Posicion> posiciones = new ArrayList<Posicion>();
	List<?> list = getHibernateTemplate().find("from Posicion");
	for (Object object : list) {
	    posiciones.add((Posicion)object);
	}
	return posiciones;
    }

}
