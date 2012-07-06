package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IUsuarioSistemaDao;
import tesis.playon.web.model.UsuarioSistema;

/**
 * 
 * @author gmorales
 * 
 */
@Repository("paisDao")
public class UsuarioSistemaDao extends CustomHibernateDaoSupport implements IUsuarioSistemaDao {

    public void save(UsuarioSistema UsuarioSistema) {
	getHibernateTemplate().save(UsuarioSistema);
    }

    public void update(UsuarioSistema UsuarioSistema) {
	getHibernateTemplate().update(UsuarioSistema);
    }

    public void delete(UsuarioSistema UsuarioSistema) {
	getHibernateTemplate().delete(UsuarioSistema);
    }

    // public UsuarioSistema findByNombreUsuarioSistema(String nombreUsuarioSistema) {
    // List<?> list = getHibernateTemplate().find("from UsuarioSistema where nombre=?", nombreUsuarioSistema);
    // return (UsuarioSistema) list.get(0);
    // }
}
