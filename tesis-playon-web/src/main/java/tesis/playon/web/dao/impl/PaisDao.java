package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.model.Pais;

/**
 * 
 * @author gmorales
 * 
 */
@Repository("paisDao")
public class PaisDao extends CustomHibernateDaoSupport implements IPaisDao {

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

    public List<Pais> findAll() {
	List<Pais> paises = new ArrayList<Pais>();
	List<?> list = getHibernateTemplate().find("from Pais");
	for (Object object : list) {
	    paises.add((Pais) object);
	}
	return paises;
    }
}
