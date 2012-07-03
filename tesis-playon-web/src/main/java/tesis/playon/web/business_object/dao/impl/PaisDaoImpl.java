package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IPaisDao;
import tesis.playon.web.model.Pais;

@Repository("paisDao")
public class PaisDaoImpl extends CustomHibernateDaoSupport implements IPaisDao {

    public void save(Pais pais) {
	getHibernateTemplate().save(pais);
    }

    public void update(Pais pais) {
	getHibernateTemplate().update(pais);
    }

    public void delete(Pais pais) {
	getHibernateTemplate().delete(pais);
    }

    public Pais findByNombrePais(String nombrePais) {
	List<?> list = getHibernateTemplate().find("from Pais where nombre=?", nombrePais);
	return (Pais) list.get(0);
    }
}
