package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ITipoEstadiaDao;
import tesis.playon.restful.domain.TipoEstadia;

@Repository("tipoEstadiaDao")
public class TipoEstadiaDao extends HibernateDaoSupport implements ITipoEstadiaDao {

    @Override
    public void save(TipoEstadia tipoEstadia) {
	getSessionFactory().getCurrentSession().save(tipoEstadia);
    }

    @Override
    public void update(TipoEstadia tipoEstadia) {
	getSessionFactory().getCurrentSession().update(tipoEstadia);
    }

    @Override
    public void delete(TipoEstadia tipoEstadia) {
	getSessionFactory().getCurrentSession().delete(tipoEstadia);
    }

    @Override
    public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoEstadia where nombre=?")
		.setParameter(0, nombreTipoEstadia).list();
	return (TipoEstadia) list.get(0);
    }

    @Override
    public List<TipoEstadia> findAll() {
	List<TipoEstadia> tipos = new ArrayList<TipoEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoEstadia order by nombre").list();
	for (Object object : list) {
	    tipos.add((TipoEstadia) object);
	}
	return tipos;
    }

}