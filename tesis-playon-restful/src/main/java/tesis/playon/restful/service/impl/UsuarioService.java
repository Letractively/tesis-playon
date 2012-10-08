package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.service.IUsuarioService;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Usuario usuario) {
	Session session = sessionFactory.getCurrentSession();
	session.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
	Session session = sessionFactory.getCurrentSession();
	session.update(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(usuario);
    }

    @Override
    public Usuario findByNombreUsuario(String usuario) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Usuario where nombreUser=?").setParameter(0, usuario).list();
	if (!list.isEmpty())
	    return (Usuario) list.get(0);
	return null;
    }

    @Override
    public List<Usuario> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<?> list = session.createQuery("from Usuario ORDER BY nombreUser").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		usuarios.add((Usuario) object);
	    }
	    return usuarios;
	}
	return null;
    }

    @Override
    public List<Usuario> findByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<?> list = session.createQuery("from Usuario where playa=?").setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		usuarios.add((Usuario) object);
	    }
	    return usuarios;
	}
	return null;
    }

    @Override
    public Usuario findGerenteByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session
		.createQuery(
			"SELECT u from Usuario as u " + "INNER JOIN RolesPorUsuario as rpu "
				+ "where (u.playa=? and rpu.rol = 'ROLE_PLAYA_GERENTE')").setParameter(0, playa).list();
	if (!list.isEmpty())
	    return (Usuario) list.get(0);
	return null;
    }
}