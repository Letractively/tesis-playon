package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IDetalleEstadiaDao;
import tesis.playon.restful.domain.DetalleEstadia;
import tesis.playon.restful.domain.Vehiculo;

@Repository("detalleEstadiaDao")
public class DetalleEstadiaDao extends HibernateDaoSupport implements IDetalleEstadiaDao {

    @Override
    public void save(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().save(detalleEstadia);
    }

    @Override
    public void update(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().update(detalleEstadia);
    }

    @Override
    public void delete(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().delete(detalleEstadia);
    }

    @Override
    public List<DetalleEstadia> findAll() {
	List<DetalleEstadia> detalleEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia").list();
	for (Object object : list) {
	    detalleEstadia.add((DetalleEstadia) object);
	}
	return detalleEstadia;
    }

    @Override
    public DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia where vehiculo=? and cobrado=0")
		.setParameter(0, vehiculo).list();
	if (!list.isEmpty()) {
	    return (DetalleEstadia) list.get(0);
	}
	return null;
    }

}