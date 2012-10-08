package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Liquidacion;
import tesis.playon.restful.service.ILiquidacionService;

@Service("liquidacionService")
public class LiquidacionService implements ILiquidacionService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Liquidacion liquidacion) {
	Session session = sessionFactory.getCurrentSession();
	session.save(liquidacion);
    }

    @Override
    public void update(Liquidacion liquidacion) {
	Session session = sessionFactory.getCurrentSession();
	session.update(liquidacion);
    }

    @Override
    public void delete(Liquidacion liquidacion) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(liquidacion);
    }

    @Override
    public List<Liquidacion> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Liquidacion> colores = new ArrayList<Liquidacion>();
	List<?> list = session.createQuery("from Liquidacion").list();
	for (Object object : list) {
	    colores.add((Liquidacion) object);
	}
	return colores;
    }

}