package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IPerfilPlayaDao;
import tesis.playon.restful.domain.PerfilPlaya;
import tesis.playon.restful.domain.Playa;

@Repository("perfilPlayaDao")
public class PerfilPlayaDao extends HibernateDaoSupport implements IPerfilPlayaDao {

    @Override
    public void save(PerfilPlaya perfilPlaya) {
	getSessionFactory().getCurrentSession().save(perfilPlaya);
    }

    @Override
    public void update(PerfilPlaya perfilPlaya) {
	getSessionFactory().getCurrentSession().update(perfilPlaya);
    }

    @Override
    public void delete(PerfilPlaya perfilPlaya) {
	getSessionFactory().getCurrentSession().delete(perfilPlaya);
    }

    @Override
    public PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from PerfilPlaya where nombre=?")
		.setParameter(0, nombrePerfilPlaya).list();
	if (!list.isEmpty())
	    return (PerfilPlaya) list.get(0);
	return null;
    }

    @Override
    public List<PerfilPlaya> findAll() {
	List<PerfilPlaya> perfiles = new ArrayList<PerfilPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from PerfilPlaya").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		perfiles.add((PerfilPlaya) object);
	    }
	    return perfiles;
	}
	return null;
    }

    @Override
    public PerfilPlaya findByPlaya(Playa playa) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from PerfilPlaya where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty())
	    return (PerfilPlaya) list.get(0);
	return null;
    }
    
}