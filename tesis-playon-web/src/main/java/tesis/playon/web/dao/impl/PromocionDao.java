package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPromocionDao;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;

/**
 * @author Pablo
 * 
 */
public class PromocionDao implements IPromocionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Promocion promocion) {
	getSessionFactory().getCurrentSession().save(promocion);
    }

    public void update(Promocion promocion) {
	getSessionFactory().getCurrentSession().update(promocion);
    }

    public void delete(Promocion promocion) {
	getSessionFactory().getCurrentSession().delete(promocion);
    }

    public Promocion findByNombrePromocion(String nombrePromocion) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Promocion where nombre=?")
		.setParameter(0, nombrePromocion).list();
	if(!list.isEmpty())
	    return (Promocion) list.get(0);
	return null;
    }

    public List<Promocion> findAll() {
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Promocion").list();
	if(!list.isEmpty()){
	    for (Object object : list) {
		    promociones.add((Promocion) object);
		}
		return promociones;
	}
	return null;
    }

    public List<Promocion> findByEstado(EstadoPromocion estado){
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Promocion where estadoPromocion=?").setParameter(0, estado).list();
	if(!list.isEmpty()){
	    for (Object object : list) {
		    promociones.add((Promocion) object);
		}
		return promociones;
	}
	return null;
    }

    public List<Promocion> findByPlaya(Playa playa, EstadoPromocion estado){
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Promocion where estadoPromocion=? and playa=?")
		.setParameter(0, estado).setParameter(1, playa).list();
	if(!list.isEmpty()){
	    for (Object object : list) {
		    promociones.add((Promocion) object);
		}
		return promociones;
	}
	return null;
    }

    public List<Promocion> findByPlaya(Playa playa){
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Promocion where playa=?")
		.setParameter(0, playa).list();
	if(!list.isEmpty()){
	    for (Object object : list) {
		    promociones.add((Promocion) object);
		}
		return promociones;
	}
	return null;
    }
    
    @Override
    public List<Promocion> findByCategoria(CategoriaVehiculo categoria, Playa playa){
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Promocion where tarifa.categoriaVehiculo=? and playa=? " +
				"and now()>=fechaInicio and now()<=fechaFin")
		.setParameter(0, categoria).setParameter(1, playa).list();
	if(!list.isEmpty()){
	    for (Object object : list) {
		if(!((Promocion)object).getTarifa().getTipoEstadia().getNombre().equals("Por Mes"))
		    promociones.add((Promocion) object);
		}
		return promociones;
	}
	return null;
    }
}
