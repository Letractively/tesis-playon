package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.domain.UsuarioSistema;
import tesis.playon.restful.service.IUsuarioSistemaService;

@Service("usuarioSistemaService")
public class UsuarioSistemaService implements IUsuarioSistemaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(UsuarioSistema usuarioSistema) {
	Session session = sessionFactory.getCurrentSession();
	session.save(usuarioSistema);
    }

    @Override
    public void update(UsuarioSistema usuarioSistema) {
	Session session = sessionFactory.getCurrentSession();
	session.update(usuarioSistema);
    }

    @Override
    public void delete(UsuarioSistema usuarioSistema) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(usuarioSistema);
    }

    @Override
    public UsuarioSistema findByNombreUsuarioSistema(Usuario usuario) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from UsuarioSistema where usuario=?")
		.setParameter(0, usuario).list();
	return (UsuarioSistema) list.get(0);
    }

    @Override
    public List<UsuarioSistema> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<UsuarioSistema> usuarios = new ArrayList<UsuarioSistema>();
	List<?> list = session.createQuery("from UsuarioSistema").list();
	for (Object object : list) {
	    usuarios.add((UsuarioSistema) object);
	}
	return usuarios;
    }
}