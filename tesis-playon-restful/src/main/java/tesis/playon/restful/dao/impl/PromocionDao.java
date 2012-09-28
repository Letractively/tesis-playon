package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IPromocionDao;
import tesis.playon.restful.domain.Promocion;

@Repository("promocionDao")
public class PromocionDao extends HibernateDaoSupport implements IPromocionDao {

    @Override
    public void save(Promocion promocion) {
	getSessionFactory().getCurrentSession().save(promocion);
    }

    @Override
    public void update(Promocion promocion) {
	getSessionFactory().getCurrentSession().update(promocion);
    }

    @Override
    public void delete(Promocion promocion) {
	getSessionFactory().getCurrentSession().delete(promocion);
    }

    @Override
    public Promocion findByNombrePromocion(String nombrePromocion) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Promocion where nombre=?")
		.setParameter(0, nombrePromocion).list();
	return (Promocion) list.get(0);
    }

    @Override
    public List<Promocion> findAll() {
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Promocion").list();
	for (Object object : list) {
	    promociones.add((Promocion) object);
	}
	return promociones;
    }
    
}