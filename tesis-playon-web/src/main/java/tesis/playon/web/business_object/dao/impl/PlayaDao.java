package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IPlayaDao;
import tesis.playon.web.model.Playa;

@Repository("playaDao")
public class PlayaDao extends CustomHibernateDaoSupport implements IPlayaDao {

    public void save(Playa playa) {
	getHibernateTemplate().save(playa);
    }

    public void update(Playa playa) {
	getHibernateTemplate().update(playa);
    }

    public void delete(Playa playa) {
	getHibernateTemplate().delete(playa);
    }

    public Playa findByNombreComercial(String nombreComercial) {
	List<?> list = getHibernateTemplate().find("from Playa where nombreComercial=?", nombreComercial);
	return (Playa) list.get(0);
    }

    public Playa findByRazonSocial(String razonSocial) {
	List<?> list = getHibernateTemplate().find("from Playa where razonSocial=?", razonSocial);
	return (Playa) list.get(0);
    }

    public List<Playa> findAll() {
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = getHibernateTemplate().find("from Playa");
	for (Object object : list) {
	    playa.add((Playa) object);
	}
	return playa;
    }

}
