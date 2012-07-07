package tesis.playon.web.business_object.dao.impl;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IPublicidadDao;
import tesis.playon.web.model.Publicidad;

/**
 * Clase DAO de Publicidad
 * @author alejandro
 * @date 07/07/2012
 */
@Repository("PublicidadDao")
public class PublicidadDao extends CustomHibernateDaoSupport implements IPublicidadDao {

    @Override
    public void save(Publicidad publicidad) {
	getHibernateTemplate().save(publicidad);
    }

    @Override
    public void update(Publicidad publicidad) {
	getHibernateTemplate().update(publicidad);
    }

    @Override
    public void delete(Publicidad publicidad) {
	getHibernateTemplate().delete(publicidad);
    }

}
