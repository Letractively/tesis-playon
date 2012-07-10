package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.ICargoEmpleadoDao;
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
    
    public List<CargoEmpleado> findAll(){
	List<CargoEmpleado> cargos = new ArrayList<CargoEmpleado>();
	List<?> list = getHibernateTemplate().find("from CargoEmpleado");
	for (Object object : list) {
	    cargos.add((CargoEmpleado)object);
	}
	return cargos;
    }
}
