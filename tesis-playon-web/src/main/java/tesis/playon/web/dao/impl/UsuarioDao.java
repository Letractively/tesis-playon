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
	getSessionFactory().getCurrentSession().save(usuario);
    }

    public void update(Usuario usuario) {
	getSessionFactory().getCurrentSession().update(usuario);
    }

    public void delete(Usuario usuario) {
	getSessionFactory().getCurrentSession().delete(usuario);
    }

    public Usuario findByNombreUsuario(String usuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Usuario where nombreUser=?")
		.setParameter(0, usuario).list();
	return (Usuario) list.get(0);
    }

    public List<Usuario> findAll() {
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Usuario").list();
	for (Object object : list) {
	    usuarios.add((Usuario) object);
	}
	return usuarios;
    }

    @Override
    public List<Usuario> findByPlaya(Playa playa) {
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Usuario where playa=?")
		.setParameter(0, playa).list();
	for (Object object : list) {
	    usuarios.add((Usuario) object);
	}
	return usuarios;
    }

    @Override
    public Usuario findGerenteByPlaya(Playa playa) {
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"SELECT u from Usuario as u "
				+ "INNER JOIN RolesPorUsuario as rpu "
				+ "where (u.playa=? and rpu.rol = 'ROLE_PLAYA_GERENTE')")
				.setParameter(0, playa).list();
	return (Usuario) list.get(0);
    }
}
