package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.ISesionDao;
import tesis.playon.restful.domain.Sesion;

public class SesionDao extends HibernateDaoSupport implements ISesionDao {

    @Override
    public void save(Sesion sesion) {
	getSessionFactory().getCurrentSession().save(sesion);
    }

    @Override
    public void update(Sesion sesion) {
	getSessionFactory().getCurrentSession().update(sesion);
    }

    @Override
    public void delete(Sesion sesion) {
	getSessionFactory().getCurrentSession().delete(sesion);
    }

    @Override
    public Sesion findByIDSesionSesion(String idSesion) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Sesion where sesionId=?")
		.setParameter(0, idSesion).list();
	return (Sesion) list.get(0);
    }

    @Override
    public List<Sesion> findAll() {
	List<Sesion> sesiones = new ArrayList<Sesion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Sesion").list();
	for (Object object : list) {
	    sesiones.add((Sesion) object);
	}
	return sesiones;
    }

}