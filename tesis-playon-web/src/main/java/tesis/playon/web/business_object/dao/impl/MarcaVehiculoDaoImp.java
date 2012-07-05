/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

/**
 * @author Pablo
 *
 */
import java.util.List;
import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IMarcaVehiculoDao;
import tesis.playon.web.model.MarcaVehiculo;

@Repository("marcaVehiculoDao")
public class MarcaVehiculoDaoImp extends CustomHibernateDaoSupport implements IMarcaVehiculoDao{
    
    public void save(MarcaVehiculo marcaVehiculo) {
	getHibernateTemplate().save(marcaVehiculo);
    }

    public void update(MarcaVehiculo marcaVehiculo) {
	getHibernateTemplate().update(marcaVehiculo);
    }

    public void delete(MarcaVehiculo marcaVehiculo) {
	getHibernateTemplate().delete(marcaVehiculo);
    }

    public MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo) {
	List<?> list = getHibernateTemplate().find("from marca_vehiculo where nombre=?", nombreMarcaVehiculo);
	return (MarcaVehiculo) list.get(0);
    }

}
