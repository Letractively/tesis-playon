package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IUsuarioSistemaDao;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.UsuarioSistema;

/**
 * 
 * @author gmorales
 * 
 */
public class UsuarioSistemaDao implements IUsuarioSistemaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(UsuarioSistema UsuarioSistema) {
	getSessionFactory().getCurrentSession().save(UsuarioSistema);
    }

    public void update(UsuarioSistema UsuarioSistema) {
	getSessionFactory().getCurrentSession().update(UsuarioSistema);
    }

    public void delete(UsuarioSistema UsuarioSistema) {
	getSessionFactory().getCurrentSession().delete(UsuarioSistema);
    }

    public UsuarioSistema findByNombreUsuarioSistema(Usuario usuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from UsuarioSistema where usuario=?")
		.setParameter(0, usuario).list();
	return (UsuarioSistema) list.get(0);
    }

    public List<UsuarioSistema> findAll() {
	List<UsuarioSistema> usuarios = new ArrayList<UsuarioSistema>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from UsuarioSistema").list();
	for (Object object : list) {
	    usuarios.add((UsuarioSistema) object);
	}
	return usuarios;
    }
}