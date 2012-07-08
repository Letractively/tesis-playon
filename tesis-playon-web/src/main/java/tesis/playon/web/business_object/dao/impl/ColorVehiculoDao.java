/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IColorVehiculoDao;
import tesis.playon.web.model.ColorVehiculo;

/**
 * @author Pablo
 *
 */
@Repository("colorVehiculoDao")
public class ColorVehiculoDao extends CustomHibernateDaoSupport implements IColorVehiculoDao {
    
    public void save(ColorVehiculo color) {
	getHibernateTemplate().save(color);
    }

    public void update(ColorVehiculo color) {
	getHibernateTemplate().update(color);
    }

    public void delete(ColorVehiculo color) {
	getHibernateTemplate().delete(color);
    }

    public ColorVehiculo findByNombreColorVehiculo(String colorVehiculo) {
	List<?> list = getHibernateTemplate().find("from ColorVehiculo where nombre=?", colorVehiculo);
	return (ColorVehiculo) list.get(0);
    }
    
    public List<ColorVehiculo> findAll(){
	List<ColorVehiculo> colores = new ArrayList<ColorVehiculo>();
	List<?> list = getHibernateTemplate().find("from ColorVehiculo");
	for (Object object : list) {
	    colores.add((ColorVehiculo) object);
	}
	return colores;
    }
}
