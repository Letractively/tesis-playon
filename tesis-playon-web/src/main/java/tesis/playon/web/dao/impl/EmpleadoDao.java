package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IEmpleadoDao;
import tesis.playon.web.model.Empleado;

/**
 * @author garribere
 * 
 */
@Repository("empleadoDao")
public class EmpleadoDao extends CustomHibernateDaoSupport implements IEmpleadoDao {

    public void save(Empleado empleado) {
	getHibernateTemplate().save(empleado);
    }

    public void update(Empleado empleado) {
	getHibernateTemplate().update(empleado);
    }

    public void delete(Empleado empleado) {
	getHibernateTemplate().delete(empleado);
    }

    public Empleado findByLegajo(Integer legajo) {
	List<?> list = getHibernateTemplate().find("from Empleado where legajo=?", legajo);
	return (Empleado) list.get(0);
    }

    public List<Empleado> findAll() {
	List<Empleado> empleados = new ArrayList<Empleado>();
	List<?> list = getHibernateTemplate().find("from Empleado");
	for (Object object : list) {
	    empleados.add((Empleado) object);
	}
	return empleados;
    }
}
