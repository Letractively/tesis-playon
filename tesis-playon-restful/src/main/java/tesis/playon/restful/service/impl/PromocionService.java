package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.Promocion;
import tesis.playon.restful.service.IPromocionService;

@Service("promocionService")
@Transactional
public class PromocionService implements IPromocionService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Promocion promocion) {
	Session session = sessionFactory.getCurrentSession();
	session.save(promocion);
    }

    @Override
    public void update(Promocion promocion) {
	Session session = sessionFactory.getCurrentSession();
	session.update(promocion);
    }

    @Override
    public void delete(Promocion promocion) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(promocion);
    }

    @Override
    public Promocion findByNombrePromocion(String nombrePromocion) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Promocion where nombre=?").setParameter(0, nombrePromocion).list();
	return (Promocion) list.get(0);
    }

    @Override
    public List<Promocion> findPromocionVigenteByPlaya(String nombreComercialPlaya) {
	Session session = sessionFactory.getCurrentSession();
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = session.createQuery("from Promocion where playa.nombreComercial=? and estadoPromocion.nombre = 'Vigente'")
		.setParameter(0, nombreComercialPlaya).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		promociones.add((Promocion) object);
	    }
	    return promociones;
	}
	return null;
    }

    @Override
    public List<Promocion> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = session.createQuery("from Promocion").list();
	for (Object object : list) {
	    promociones.add((Promocion) object);
	}
	return promociones;
    }
}