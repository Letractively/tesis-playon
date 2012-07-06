package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IPerfilPlayaDao;
import tesis.playon.web.model.PerfilPlaya;

/**
 * 
 * @author gmorales
 * 
 */
@Repository("perfilPlayaDao")
public class PerfilPlayaDao extends CustomHibernateDaoSupport implements IPerfilPlayaDao {

    public void save(PerfilPlaya perfilPlaya) {
	getHibernateTemplate().save(perfilPlaya);
    }

    public void update(PerfilPlaya perfilPlaya) {
	getHibernateTemplate().update(perfilPlaya);
    }

    public void delete(PerfilPlaya perfilPlaya) {
	getHibernateTemplate().delete(perfilPlaya);
    }

    public PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya) {
	List<?> list = getHibernateTemplate().find("from PerfilPlaya where nombre=?", nombrePerfilPlaya);
	return (PerfilPlaya) list.get(0);
    }

}
