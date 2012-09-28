package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IRolUsuarioDao;
import tesis.playon.restful.domain.RolUsuario;

@Repository("rolUsuarioDao")
public class RolUsuarioDao extends HibernateDaoSupport implements IRolUsuarioDao {

    @Override
    public void save(RolUsuario rolUsuario) {
	getSessionFactory().getCurrentSession().save(rolUsuario);
    }

    @Override
    public void update(RolUsuario rolUsuario) {
	getSessionFactory().getCurrentSession().update(rolUsuario);
    }

    @Override
    public void delete(RolUsuario rolUsuario) {
	getSessionFactory().getCurrentSession().delete(rolUsuario);
    }

    @Override
    public List<RolUsuario> findAll() {
	List<RolUsuario> listaRolUsuario = new ArrayList<RolUsuario>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from RolUsuario order by nombre").list();
	for (Object obj : list) {
	    listaRolUsuario.add((RolUsuario) obj);
	}
	return listaRolUsuario;
    }

    @Override
    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from RolUsuario where nombre=?")
		.setParameter(0, nombreRolUsuario).list();
	return (RolUsuario) list.get(0);
    }

}