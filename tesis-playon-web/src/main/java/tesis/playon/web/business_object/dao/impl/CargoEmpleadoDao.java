/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ICargoEmpleadoDao;
import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 *
 */
@Repository("cargoEmpleadoDao")
public class CargoEmpleadoDao extends CustomHibernateDaoSupport implements ICargoEmpleadoDao{

    public void save(CargoEmpleado cargo) {
	getHibernateTemplate().save(cargo);
    }

    public void update(CargoEmpleado cargo) {
	getHibernateTemplate().update(cargo);
    }

    public void delete(CargoEmpleado cargo) {
	getHibernateTemplate().delete(cargo);
    }

    public CargoEmpleado findByNombreCargo(String nombreCargo) {
	List<?> list = getHibernateTemplate().find("from CargoEmpleado where nombre=?", nombreCargo);
	return (CargoEmpleado) list.get(0);
    }
}
