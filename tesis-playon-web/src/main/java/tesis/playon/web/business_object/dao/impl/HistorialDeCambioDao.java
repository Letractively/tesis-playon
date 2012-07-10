package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IHistorialDeCambioDao;
import tesis.playon.web.model.HistorialDeCambio;

@Repository("historialDeCambioDao")
public class HistorialDeCambioDao extends CustomHibernateDaoSupport implements IHistorialDeCambioDao {

    public void save(HistorialDeCambio historialDeCambio) {
	getHibernateTemplate().save(historialDeCambio);
    }

    public void update(HistorialDeCambio historialDeCambio) {
	getHibernateTemplate().update(historialDeCambio);
    }

    public void delete(HistorialDeCambio historialDeCambio) {
	getHibernateTemplate().delete(historialDeCambio);
    }

    public List<HistorialDeCambio> findAll() {
	List<HistorialDeCambio> historialDeCambio = new ArrayList<HistorialDeCambio>();
	List<?> list = getHibernateTemplate().find("from HistorialDeCambio");
	for (Object object : list) {
	    historialDeCambio.add((HistorialDeCambio) object);
	}
	return historialDeCambio;
    }

}
