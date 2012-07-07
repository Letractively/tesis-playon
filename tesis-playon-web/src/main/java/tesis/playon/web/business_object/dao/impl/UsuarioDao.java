package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IUsuarioDao;
import tesis.playon.web.model.Usuario;

/**
 * 
 * @author garribere
 *
 */
@Repository("usuarioDao")
public class UsuarioDao extends CustomHibernateDaoSupport implements IUsuarioDao {

    public void save(Usuario usuario) {
	getHibernateTemplate().save(usuario);
    }

    public void update(Usuario usuario) {
	getHibernateTemplate().update(usuario);
    }

    public void delete(Usuario usuario) {
	getHibernateTemplate().delete(usuario);
    }

    public Usuario findByNombreUsuario(String usuario) {
	List<?> list = getHibernateTemplate().find("from Usuario where usuario=?", usuario);
	return (Usuario) list.get(0);
    }
    
}
