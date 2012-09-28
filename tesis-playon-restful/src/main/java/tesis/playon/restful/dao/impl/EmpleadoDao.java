package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IEmpleadoDao;
import tesis.playon.restful.domain.Empleado;
import tesis.playon.restful.domain.Usuario;

@Repository("empleadoDao")
public class EmpleadoDao extends HibernateDaoSupport implements IEmpleadoDao {

    @Override
    public void save(Empleado empleado) {
	getSessionFactory().getCurrentSession().save(empleado);
    }

    @Override
    public void update(Empleado empleado) {
	getSessionFactory().getCurrentSession().update(empleado);
    }

    @Override
    public void delete(Empleado empleado) {
	getSessionFactory().getCurrentSession().delete(empleado);
    }

    @Override
    public List<Empleado> findAll() {
	List<Empleado> empleados = new ArrayList<Empleado>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado").list();
	for (Object object : list) {
	    empleados.add((Empleado) object);
	}
	return empleados;
    }

    @Override
    public List<Empleado> findAll(Integer idCargoEmpleado) {
	List<Empleado> empleados = new ArrayList<Empleado>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where cargoEmpleado.id=?")
		.setParameter(0, idCargoEmpleado).list();
	for (Object object : list) {
	    empleados.add((Empleado) object);
	}
	return empleados;
    }

    @Override
    public Empleado findById(Integer id) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where id=?")
		.setParameter(0, id).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }

    @Override
    public Empleado findByIdUsuario(Usuario usuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where usuario=?")
		.setParameter(0, usuario).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }

    @Override
    public Empleado findByLegajo(Integer legajo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where legajo=?")
		.setParameter(0, legajo).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }
    
}