package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.RolUsuario;
import tesis.playon.restful.service.IRolUsuarioService;

@Service("rolUsuarioService")
public class RolUsuarioService implements IRolUsuarioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(RolUsuario rolUsuario) {
	Session session = sessionFactory.getCurrentSession();
	session.save(rolUsuario);
    }

    @Override
    public void update(RolUsuario rolUsuario) {
	Session session = sessionFactory.getCurrentSession();
	session.update(rolUsuario);
    }

    @Override
    public void delete(RolUsuario rolUsuario) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(rolUsuario);
    }

    @Override
    public List<RolUsuario> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<RolUsuario> listaRolUsuario = new ArrayList<RolUsuario>();
	List<?> list = session.createQuery("from RolUsuario order by nombre").list();
	for (Object obj : list) {
	    listaRolUsuario.add((RolUsuario) obj);
	}
	return listaRolUsuario;
    }

    @Override
    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from RolUsuario where nombre=?")
		.setParameter(0, nombreRolUsuario).list();
	return (RolUsuario) list.get(0);
    }
}