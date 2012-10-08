package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.TipoPago;
import tesis.playon.restful.service.ITipoPagoService;

@Service("tipoPagoService")
@Transactional
public class TipoPagoService implements ITipoPagoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(TipoPago tipoPago) {
	Session session = sessionFactory.getCurrentSession();
	session.save(tipoPago);
    }

    @Override
    public void update(TipoPago tipoPago) {
	Session session = sessionFactory.getCurrentSession();
	session.update(tipoPago);
    }

    @Override
    public void delete(TipoPago tipoPago) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(tipoPago);
    }

    @Override
    public TipoPago findByNameTipoPago(String nombreTipoPago) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from TipoPago where nombre=?")
		.setParameter(0, nombreTipoPago).list();
	return (TipoPago) list.get(0);
    }

    @Override
    public List<TipoPago> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<TipoPago> tipos = new ArrayList<TipoPago>();
	List<?> list = session.createQuery("from TipoPago order by nombre").list();
	for (Object object : list) {
	    tipos.add((TipoPago) object);
	}
	return tipos;
    }
    
}