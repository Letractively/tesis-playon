package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Empleado;
import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.service.IEmpleadoService;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Empleado empleado) {
	Session session = sessionFactory.getCurrentSession();
	session.save(empleado);
    }

    @Override
    public void update(Empleado empleado) {
	Session session = sessionFactory.getCurrentSession();
	session.update(empleado);
    }

    @Override
    public void delete(Empleado empleado) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(empleado);
    }

    @Override
    public List<Empleado> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Empleado> empleados = new ArrayList<Empleado>();
	List<?> list = session.createQuery("from Empleado").list();
	for (Object object : list) {
	    empleados.add((Empleado) object);
	}
	return empleados;
    }

    @Override
    public List<Empleado> findAll(Integer idCargoEmpleado) {
	Session session = sessionFactory.getCurrentSession();
	List<Empleado> empleados = new ArrayList<Empleado>();
	List<?> list = session.createQuery("from Empleado where cargoEmpleado.id=?")
		.setParameter(0, idCargoEmpleado).list();
	for (Object object : list) {
	    empleados.add((Empleado) object);
	}
	return empleados;
    }

    @Override
    public Empleado findById(Integer id) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Empleado where id=?")
		.setParameter(0, id).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }

    @Override
    public Empleado findByIdUsuario(Usuario usuario) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Empleado where usuario=?")
		.setParameter(0, usuario).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }

    @Override
    public Empleado findByLegajo(Integer legajo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Empleado where legajo=?")
		.setParameter(0, legajo).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }
}