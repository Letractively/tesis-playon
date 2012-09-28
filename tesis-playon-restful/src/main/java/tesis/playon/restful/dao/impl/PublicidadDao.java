package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IPublicidadDao;
import tesis.playon.restful.domain.Publicidad;

@Repository("publicidadDao")
public class PublicidadDao extends HibernateDaoSupport implements IPublicidadDao {

    @Override
    public void save(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().save(publicidad);
    }

    @Override
    public void update(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().update(publicidad);
    }

    @Override
    public void delete(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().delete(publicidad);
    }

    @Override
    public List<Publicidad> findAll() {
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Publicidad").list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }
    
}