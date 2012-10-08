package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ICargoEmpleadoDao;
import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 * 
 */
public class CargoEmpleadoDao implements ICargoEmpleadoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(CargoEmpleado cargo) {
	session.save(cargo);
    }

    public void update(CargoEmpleado cargo) {
	session.update(cargo);
    }

    public void delete(CargoEmpleado cargo) {
	session.delete(cargo);
    }

    public CargoEmpleado findByNombreCargo(String nombreCargo) {
	List<?> list = session.createQuery("from CargoEmpleado where nombre=?")
		.setParameter(0, nombreCargo).list();
	return (CargoEmpleado) list.get(0);
    }

    public List<CargoEmpleado> findAll() {
	List<CargoEmpleado> cargos = new ArrayList<CargoEmpleado>();
	List<?> list = session.createQuery("from CargoEmpleado order by nombre").list();
	for (Object object : list) {
	    cargos.add((CargoEmpleado) object);
	}
	return cargos;
    }

    public CargoEmpleado findById(Integer id) {
	List<?> list = session.createQuery("from CargoEmpleado where id=?")
		.setParameter(0, id).list();
	return (CargoEmpleado) list.get(0);

    }
}
