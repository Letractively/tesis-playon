/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IEmpleadoDao;
import tesis.playon.web.model.Empleado;

/**
 * @author garribere
 *
 */
@Repository("empleadoDao")
public class EmpleadoDao extends CustomHibernateDaoSupport implements IEmpleadoDao{
    
    public void save(Empleado empleado) {
	getHibernateTemplate().save(empleado);
    }

    public void update(Empleado empleado) {
	getHibernateTemplate().update(empleado);
    }

    public void delete(Empleado empleado) {
	getHibernateTemplate().delete(empleado);
    }

    public Empleado findByLegajo(String legajo) {
	List<?> list = getHibernateTemplate().find("from Empleado where legajo=?", legajo);
	return (Empleado) list.get(0);
    }

}
