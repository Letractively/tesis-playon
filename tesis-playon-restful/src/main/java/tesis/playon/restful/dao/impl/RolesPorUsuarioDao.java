package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IRolesPorUsuarioDao;
import tesis.playon.restful.domain.RolesPorUsuario;

@Repository("rolesPorUsuarioDao")
public class RolesPorUsuarioDao extends HibernateDaoSupport implements IRolesPorUsuarioDao {

    @Override
    public void save(RolesPorUsuario rolPorUser) {
	getSessionFactory().getCurrentSession().save(rolPorUser);
    }

    @Override
    public void update(RolesPorUsuario rolPorUser) {
	getSessionFactory().getCurrentSession().update(rolPorUser);
    }

    @Override
    public void delete(RolesPorUsuario rolPorUser) {
	getSessionFactory().getCurrentSession().delete(rolPorUser);
    }

    @Override
    public RolesPorUsuario findByNombreUsuario(String nombreUser) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from RolesPorUsuario where usuario=?")
		.setParameter(0, nombreUser).list();
	return (RolesPorUsuario) list.get(0);
    }

    @Override
    public List<RolesPorUsuario> findAll() {
	List<RolesPorUsuario> rolesPorUser = new ArrayList<RolesPorUsuario>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from RolesPorUsuario").list();
	for (Object object : list) {
	    rolesPorUser.add((RolesPorUsuario) object);
	}
	return rolesPorUser;
    }
    
}