package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IPosicionDao;
import tesis.playon.restful.domain.Posicion;

@Repository("posicionDao")
public class PosicionDao extends HibernateDaoSupport implements IPosicionDao {

    @Override
    public void save(Posicion posicion) {
	getSessionFactory().getCurrentSession().save(posicion);
    }

    @Override
    public void update(Posicion posicion) {
	getSessionFactory().getCurrentSession().update(posicion);
    }

    @Override
    public void delete(Posicion posicion) {
	getSessionFactory().getCurrentSession().delete(posicion);
    }

    @Override
    public Posicion findByUbicacion(String ubicacion) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Posicion where ubicacion=?")
		.setParameter(0, ubicacion).list();
	return (Posicion) list.get(0);
    }

    @Override
    public List<Posicion> findAll() {
	List<Posicion> posiciones = new ArrayList<Posicion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Posicion").list();
	for (Object object : list) {
	    posiciones.add((Posicion) object);
	}
	return posiciones;
    }

}