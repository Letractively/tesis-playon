package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IRolesPorUsuarioDao;
import tesis.playon.web.model.RolesPorUsuario;

/**
 * 
 * @author garribere
 * 
 */
public class RolesPorUsuarioDao implements IRolesPorUsuarioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(RolesPorUsuario rolPorUser) {
	session.save(rolPorUser);
    }

    public void update(RolesPorUsuario rolPorUser) {
	session.update(rolPorUser);
    }

    public void delete(RolesPorUsuario rolPorUser) {
	session.delete(rolPorUser);
    }

    public RolesPorUsuario findByNombreUsuario(String nombreUser) {
	List<?> list = session.createQuery("from RolesPorUsuario where usuario=?")
		.setParameter(0, nombreUser).list();
	return (RolesPorUsuario) list.get(0);
    }

    public List<RolesPorUsuario> findAll() {
	List<RolesPorUsuario> rolesPorUser = new ArrayList<RolesPorUsuario>();
	List<?> list = session.createQuery("from RolesPorUsuario").list();
	for (Object object : list) {
	    rolesPorUser.add((RolesPorUsuario) object);
	}
	return rolesPorUser;
    }
}
