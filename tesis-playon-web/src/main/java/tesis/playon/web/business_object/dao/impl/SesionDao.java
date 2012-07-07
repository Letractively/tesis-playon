package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ISesionDao;
import tesis.playon.web.model.Sesion;

@Repository("SesionDao")
public class SesionDao extends CustomHibernateDaoSupport implements ISesionDao {

    @Override
    public void save(Sesion sesion) {
	getHibernateTemplate().save(sesion);
    }

    @Override
    public void update(Sesion sesion) {
	getHibernateTemplate().update(sesion);
    }

    @Override
    public void delete(Sesion sesion) {
	getHibernateTemplate().delete(sesion);
    }

    @Override
    public Sesion findByIDSesionSesion(String idSesion) {
	List<?> list = getHibernateTemplate().find("from Sesion where sesionId=?", idSesion);
	return (Sesion) list.get(0);
    }

}
