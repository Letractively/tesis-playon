package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
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

    public void save(RolUsuario rolUsuario) {
	getHibernateTemplate().save(rolUsuario);
    }

    public void update(RolUsuario rolUsuario) {
	getHibernateTemplate().update(rolUsuario);
    }

    public void delete(RolUsuario rolUsuario) {
	getHibernateTemplate().delete(rolUsuario);
    }

    public List<RolUsuario> findAll() {
	List<RolUsuario> listaRolUsuario = new ArrayList<RolUsuario>();
	List<?> list = getHibernateTemplate().find("from RolUsuario");
	for (Object obj : list) {
	    listaRolUsuario.add((RolUsuario) obj);
	}
	return listaRolUsuario;
    }

    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	List<?> list = getHibernateTemplate().find("from RolUsuario where nombre=?", nombreRolUsuario);
	return (RolUsuario) list.get(0);
    }

}
