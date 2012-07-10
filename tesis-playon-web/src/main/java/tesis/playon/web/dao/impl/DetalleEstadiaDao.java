package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IDetalleEstadiaDao;
import tesis.playon.web.model.DetalleEstadia;

@Repository("detalleEstadiaDao")
public class DetalleEstadiaDao extends CustomHibernateDaoSupport implements IDetalleEstadiaDao {

    public void save(DetalleEstadia detalleEstadia) {
	getHibernateTemplate().save(detalleEstadia);
    }

    public void update(DetalleEstadia detalleEstadia) {
	getHibernateTemplate().update(detalleEstadia);
    }

    public void delete(DetalleEstadia detalleEstadia) {
	getHibernateTemplate().delete(detalleEstadia);
    }

    public List<DetalleEstadia> findAll() {
	List<DetalleEstadia> detalleEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getHibernateTemplate().find("from DetalleEstadia");
	for (Object object : list) {
	    detalleEstadia.add((DetalleEstadia) object);
	}
	return detalleEstadia;
    }
}
