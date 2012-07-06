/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IVehiculoDao;
import tesis.playon.web.model.Vehiculo;

/**
 * @author Pablo
 *
 */
@Repository("VehiculoDao")
public class VehiculoDao extends CustomHibernateDaoSupport implements IVehiculoDao{
    
    public void save(Vehiculo vehiculo) {
	getHibernateTemplate().save(vehiculo);
    }

    public void update(Vehiculo vehiculo) {
	getHibernateTemplate().update(vehiculo);
    }

    public void delete(Vehiculo vehiculo) {
	getHibernateTemplate().delete(vehiculo);
    }

    public Vehiculo findByPatenteVehiculo(String patenteVehiculo) {
	List<?> list = getHibernateTemplate().find("from Vehiculo where patente=?", patenteVehiculo);
	return (Vehiculo) list.get(0);
    }

}
