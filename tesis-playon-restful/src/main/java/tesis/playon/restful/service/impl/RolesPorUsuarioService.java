package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.RolesPorUsuario;
import tesis.playon.restful.service.IRolesPorUsuarioService;

@Service("rolesPorUsuarioService")
public class RolesPorUsuarioService implements IRolesPorUsuarioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(RolesPorUsuario rolPorUser) {
	Session session = sessionFactory.getCurrentSession();
	session.save(rolPorUser);
    }

    @Override
    public void update(RolesPorUsuario rolPorUser) {
	Session session = sessionFactory.getCurrentSession();
	session.update(rolPorUser);
    }

    @Override
    public void delete(RolesPorUsuario rolPorUser) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(rolPorUser);
    }

    @Override
    public RolesPorUsuario findByNombreUsuario(String nombreUser) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from RolesPorUsuario where usuario=?")
		.setParameter(0, nombreUser).list();
	return (RolesPorUsuario) list.get(0);
    }

    @Override
    public List<RolesPorUsuario> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<RolesPorUsuario> rolesPorUser = new ArrayList<RolesPorUsuario>();
	List<?> list = session.createQuery("from RolesPorUsuario").list();
	for (Object object : list) {
	    rolesPorUser.add((RolesPorUsuario) object);
	}
	return rolesPorUser;
    }
}