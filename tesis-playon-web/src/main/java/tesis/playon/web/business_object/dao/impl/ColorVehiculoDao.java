/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

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
}
