/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ICategoriaVehiculoDao;
import tesis.playon.web.model.CategoriaVehiculo;

/**
 * @author Pablo
 *
 */
@Repository("CategoriaVehiculoDao")
public class CategoriaVehiculoDao extends CustomHibernateDaoSupport implements ICategoriaVehiculoDao{
    
    
    public void save(CategoriaVehiculo categoria) {
	getHibernateTemplate().save(categoria);
    }

    public void update(CategoriaVehiculo categoria) {
	getHibernateTemplate().update(categoria);
    }

    public void delete(CategoriaVehiculo categoria) {
	getHibernateTemplate().delete(categoria);
    }

    public CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoria) {
	List<?> list = getHibernateTemplate().find("from CategoriaVehiculo where nombre=?", nombreCategoria);
	return (CategoriaVehiculo) list.get(0);
    }
    

}
