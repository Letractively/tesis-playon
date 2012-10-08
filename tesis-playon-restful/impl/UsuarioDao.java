package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IUsuarioDao;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;

/**
 * 
 * @author garribere
 * 
 */
public class UsuarioDao implements IUsuarioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Usuario usuario) {
	session.save(usuario);
    }

    public void update(Usuario usuario) {
	session.update(usuario);
    }

    public void delete(Usuario usuario) {
	session.delete(usuario);
    }

    public Usuario findByNombreUsuario(String usuario) {
	List<?> list = session.createQuery("from Usuario where nombreUser=?")
		.setParameter(0, usuario).list();
	if (!list.isEmpty())
	    return (Usuario) list.get(0);
	return null;
    }

    public List<Usuario> findAll() {
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
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<?> list = session.createQuery("from Usuario where playa=?")
		.setParameter(0, playa).list();
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
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"SELECT u from Usuario as u " + "INNER JOIN RolesPorUsuario as rpu "
				+ "where (u.playa=? and rpu.rol = 'ROLE_PLAYA_GERENTE')").setParameter(0, playa).list();
	if (!list.isEmpty())
	    return (Usuario) list.get(0);
	return null;
    }
}
