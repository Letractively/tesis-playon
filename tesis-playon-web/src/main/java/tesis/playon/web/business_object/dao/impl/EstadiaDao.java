package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IEstadiaDao;
import tesis.playon.web.model.Estadia;

@Repository("estadiaDao")
public class EstadiaDao extends CustomHibernateDaoSupport implements IEstadiaDao {

    public void save(Estadia estadia) {
	getHibernateTemplate().save(estadia);
    }

    public void update(Estadia estadia) {
	getHibernateTemplate().update(estadia);
    }

    public void delete(Estadia estadia) {
	getHibernateTemplate().delete(estadia);
    }

    public List<Estadia> findAll() {
	List<Estadia> colores = new ArrayList<Estadia>();
	List<?> list = getHibernateTemplate().find("from Estadia");
	for (Object object : list) {
	    colores.add((Estadia) object);
	}
	return colores;
    }

}
