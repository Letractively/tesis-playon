package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.IUsuarioDao;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.Usuario;

public class UsuarioDao extends HibernateDaoSupport implements IUsuarioDao {

    @Override
    public void save(Usuario usuario) {
	getSessionFactory().getCurrentSession().save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
	getSessionFactory().getCurrentSession().update(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
	getSessionFactory().getCurrentSession().delete(usuario);
    }

    @Override
    public Usuario findByNombreUsuario(String usuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Usuario where nombreUser=?")
		.setParameter(0, usuario).list();
	if (!list.isEmpty())
	    return (Usuario) list.get(0);
	return null;
    }

    @Override
    public List<Usuario> findAll() {
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Usuario ORDER BY nombreUser").list();
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
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Usuario where playa=?")
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