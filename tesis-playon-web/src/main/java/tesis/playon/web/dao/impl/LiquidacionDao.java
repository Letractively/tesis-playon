package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
		getSessionFactory().getCurrentSession().save(liquidacion);
	}

	public void update(Liquidacion liquidacion) {
		getSessionFactory().getCurrentSession().update(liquidacion);
	}

	public void delete(Liquidacion liquidacion) {
		getSessionFactory().getCurrentSession().delete(liquidacion);
	}

	public List<Liquidacion> findAll() {
		List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from Liquidacion").list();
		for (Object object : list) {
			liquidaciones.add((Liquidacion) object);
		}
		return liquidaciones;
	}

	public List<Liquidacion> findByFecha(Date fecha) {
		List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from Liquidacion where fecha>=?")
				.setParameter(0, fecha).list();
		for (Object object : list) {
			liquidaciones.add((Liquidacion) object);
		}
		return liquidaciones;
	}

	public List<Liquidacion> findByFecha(Date fechaDesde, Date fechaHasta) {
		List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from Liquidacion where fecha>=? and fecha<=?")
				.setParameter(0, fechaDesde).setParameter(1, fechaHasta).list();
		for (Object object : list) {
			liquidaciones.add((Liquidacion) object);
		}
		return liquidaciones;
	}

}
