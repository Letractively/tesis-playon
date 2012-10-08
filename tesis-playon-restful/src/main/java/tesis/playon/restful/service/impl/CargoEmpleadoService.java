package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.CargoEmpleado;
import tesis.playon.restful.service.ICargoEmpleadoService;

@Service("cargoEmpleadoService")
@Transactional
public class CargoEmpleadoService implements ICargoEmpleadoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(CargoEmpleado cargo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(cargo);
    }

    @Override
    public void update(CargoEmpleado cargo) {
	Session session = sessionFactory.getCurrentSession();
	session.update(cargo);
    }

    @Override
    public void delete(CargoEmpleado cargo) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(cargo);
    }

    @Override
    public CargoEmpleado findByNombreCargo(String nombreCargo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from CargoEmpleado where nombre=?")
		.setParameter(0, nombreCargo).list();
	return (CargoEmpleado) list.get(0);
    }

    @Override
    public List<CargoEmpleado> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<CargoEmpleado> cargos = new ArrayList<CargoEmpleado>();
	List<?> list = session.createQuery("from CargoEmpleado order by nombre").list();
	for (Object object : list) {
	    cargos.add((CargoEmpleado) object);
	}
	return cargos;
    }

    @Override
    public CargoEmpleado findById(Integer id) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from CargoEmpleado where id=?")
		.setParameter(0, id).list();
	return (CargoEmpleado) list.get(0);
    }
    
}