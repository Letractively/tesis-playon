package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IUsuarioSistemaDao;
import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.domain.UsuarioSistema;

@Repository("usuarioSistemaDao")
public class UsuarioSistemaDao extends HibernateDaoSupport implements IUsuarioSistemaDao {

    @Override
    public void save(UsuarioSistema usuarioSistema) {
	getSessionFactory().getCurrentSession().save(usuarioSistema);
    }

    @Override
    public void update(UsuarioSistema usuarioSistema) {
	getSessionFactory().getCurrentSession().update(usuarioSistema);
    }

    @Override
    public void delete(UsuarioSistema usuarioSistema) {
	getSessionFactory().getCurrentSession().delete(usuarioSistema);
    }

    @Override
    public UsuarioSistema findByNombreUsuarioSistema(Usuario usuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from UsuarioSistema where usuario=?")
		.setParameter(0, usuario).list();
	return (UsuarioSistema) list.get(0);
    }

    @Override
    public List<UsuarioSistema> findAll() {
	List<UsuarioSistema> usuarios = new ArrayList<UsuarioSistema>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from UsuarioSistema").list();
	for (Object object : list) {
	    usuarios.add((UsuarioSistema) object);
	}
	return usuarios;
    }
}