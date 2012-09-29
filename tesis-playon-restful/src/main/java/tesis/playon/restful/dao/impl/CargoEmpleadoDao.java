package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.ICargoEmpleadoDao;
import tesis.playon.restful.domain.CargoEmpleado;

public class CargoEmpleadoDao extends HibernateDaoSupport implements ICargoEmpleadoDao {

    @Override
    public void save(CargoEmpleado cargo) {
	getSessionFactory().getCurrentSession().save(cargo);
    }

    @Override
    public void update(CargoEmpleado cargo) {
	getSessionFactory().getCurrentSession().update(cargo);
    }

    @Override
    public void delete(CargoEmpleado cargo) {
	getSessionFactory().getCurrentSession().delete(cargo);
    }

    @Override
    public CargoEmpleado findByNombreCargo(String nombreCargo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CargoEmpleado where nombre=?")
		.setParameter(0, nombreCargo).list();
	return (CargoEmpleado) list.get(0);
    }

    @Override
    public List<CargoEmpleado> findAll() {
	List<CargoEmpleado> cargos = new ArrayList<CargoEmpleado>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CargoEmpleado order by nombre").list();
	for (Object object : list) {
	    cargos.add((CargoEmpleado) object);
	}
	return cargos;
    }

    @Override
    public CargoEmpleado findById(Integer id) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CargoEmpleado where id=?")
		.setParameter(0, id).list();
	return (CargoEmpleado) list.get(0);
    }
    
}