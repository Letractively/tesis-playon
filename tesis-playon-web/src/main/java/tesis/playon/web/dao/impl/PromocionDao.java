package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IPromocionDao;
import tesis.playon.web.model.Promocion;

/**
 * @author Pablo
 *
 */
@Repository("promocionDao")
public class PromocionDao extends CustomHibernateDaoSupport implements IPromocionDao{

    public void save(Promocion promocion) {
	getHibernateTemplate().save(promocion);
    }

    public void update(Promocion promocion) {
	getHibernateTemplate().update(promocion);
    }

    public void delete(Promocion promocion) {
	getHibernateTemplate().delete(promocion);
    }

    public Promocion findByNombrePromocion(String nombrePromocion) {
	List<?> list = getHibernateTemplate().find("from Promocion where nombre=?", nombrePromocion);
	return (Promocion) list.get(0);
    }
    
    public List<Promocion> findAll(){
	List<Promocion> promociones = new ArrayList<Promocion>();
	List<?> list = getHibernateTemplate().find("from Promocion");
	for (Object object : list) {
	    promociones.add((Promocion)object);
	}
	return promociones;
    }
}
