package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ILiquidacionDao;
import tesis.playon.web.model.Liquidacion;

public class LiquidacionDao implements ILiquidacionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Liquidacion liquidacion) {
	session.save(liquidacion);
    }

    public void update(Liquidacion liquidacion) {
	session.update(liquidacion);
    }

    public void delete(Liquidacion liquidacion) {
	session.delete(liquidacion);
    }

    public List<Liquidacion> findAll() {
	List<Liquidacion> colores = new ArrayList<Liquidacion>();
	List<?> list = session.createQuery("from Liquidacion").list();
	for (Object object : list) {
	    colores.add((Liquidacion) object);
	}
	return colores;
    }

}
