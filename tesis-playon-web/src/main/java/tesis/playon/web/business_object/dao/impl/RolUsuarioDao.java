package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IRolUsuarioDao;
import tesis.playon.web.model.RolUsuario;

/**
 * 
 * @author gmorales
 *
 */
@Repository("rolUsuarioDao")
public class RolUsuarioDao extends CustomHibernateDaoSupport implements IRolUsuarioDao {

    public void save(RolUsuario cliente) {
	getHibernateTemplate().save(cliente);
    }

    public void update(RolUsuario cliente) {
	getHibernateTemplate().update(cliente);
    }

    public void delete(RolUsuario cliente) {
	getHibernateTemplate().delete(cliente);
    }

    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	List<?> list = getHibernateTemplate().find("from rol_usuario where nombre=?", nombreRolUsuario);
	return (RolUsuario) list.get(0);
    }
    
}
