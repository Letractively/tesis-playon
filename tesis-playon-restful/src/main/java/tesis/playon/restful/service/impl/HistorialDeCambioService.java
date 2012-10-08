package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.HistorialDeCambio;
import tesis.playon.restful.service.IHistorialDeCambioService;

@Service("historialDeCambioService")
@Transactional
public class HistorialDeCambioService implements IHistorialDeCambioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(HistorialDeCambio historialDeCambio) {
	Session session = sessionFactory.getCurrentSession();
	session.save(historialDeCambio);
    }

    @Override
    public void update(HistorialDeCambio historialDeCambio) {
	Session session = sessionFactory.getCurrentSession();
	session.update(historialDeCambio);
    }

    @Override
    public void delete(HistorialDeCambio historialDeCambio) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(historialDeCambio);
    }

    @Override
    public List<HistorialDeCambio> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<HistorialDeCambio> historialDeCambio = new ArrayList<HistorialDeCambio>();
	List<?> list = session.createQuery("from HistorialDeCambio").list();
	for (Object object : list) {
	    historialDeCambio.add((HistorialDeCambio) object);
	}
	return historialDeCambio;
    }
}