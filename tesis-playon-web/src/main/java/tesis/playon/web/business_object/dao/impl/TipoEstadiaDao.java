package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ITipoEstadiaDao;
import tesis.playon.web.model.TipoEstadia;

/**
 * 
 * @author garribere
 * 
 */
@Repository("tipoEstadiaDao")
public class TipoEstadiaDao extends CustomHibernateDaoSupport implements ITipoEstadiaDao {

    public void save(TipoEstadia tipoEstadia) {
	getHibernateTemplate().save(tipoEstadia);
    }

    public void update(TipoEstadia tipoEstadia) {
	getHibernateTemplate().update(tipoEstadia);
    }

    public void delete(TipoEstadia tipoEstadia) {
	getHibernateTemplate().delete(tipoEstadia);
    }

    public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
	List<?> list = getHibernateTemplate().find("from TipoEstadia where nombre=?", nombreTipoEstadia);
	return (TipoEstadia) list.get(0);
    }

    public List<TipoEstadia> findAll() {
	List<TipoEstadia> tipos = new ArrayList<TipoEstadia>();
	List<?> list = getHibernateTemplate().find("from TipoEstadia");
	for (Object object : list) {
	    tipos.add((TipoEstadia) object);
	}
	return tipos;
    }

}
