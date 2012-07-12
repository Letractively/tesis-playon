package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IPublicidadDao;
import tesis.playon.web.model.Publicidad;

/**
 * Clase DAO de Publicidad
 * @author alejandro
 * @date 07/07/2012
 */
@Repository("PublicidadDao")
public class PublicidadDao extends CustomHibernateDaoSupport implements IPublicidadDao {

    public void save(Publicidad publicidad) {
	getHibernateTemplate().save(publicidad);
    }

    public void update(Publicidad publicidad) {
	getHibernateTemplate().update(publicidad);
    }

    public void delete(Publicidad publicidad) {
	getHibernateTemplate().delete(publicidad);
    }

    public List<Publicidad> findAll(){
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getHibernateTemplate().find("from Publicidad");
	for (Object object : list) {
	    publicidades.add((Publicidad)object);
	}
	return publicidades;
    }
}
