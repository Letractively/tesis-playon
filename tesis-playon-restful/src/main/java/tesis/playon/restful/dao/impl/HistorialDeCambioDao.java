package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.IHistorialDeCambioDao;
import tesis.playon.restful.domain.HistorialDeCambio;

public class HistorialDeCambioDao extends HibernateDaoSupport implements IHistorialDeCambioDao {

    @Override
    public void save(HistorialDeCambio historialDeCambio) {
	getSessionFactory().getCurrentSession().save(historialDeCambio);
    }

    @Override
    public void update(HistorialDeCambio historialDeCambio) {
	getSessionFactory().getCurrentSession().update(historialDeCambio);
    }

    @Override
    public void delete(HistorialDeCambio historialDeCambio) {
	getSessionFactory().getCurrentSession().delete(historialDeCambio);
    }

    @Override
    public List<HistorialDeCambio> findAll() {
	List<HistorialDeCambio> historialDeCambio = new ArrayList<HistorialDeCambio>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from HistorialDeCambio").list();
	for (Object object : list) {
	    historialDeCambio.add((HistorialDeCambio) object);
	}
	return historialDeCambio;
    }

}