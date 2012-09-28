package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IPaisDao;
import tesis.playon.restful.domain.Pais;
import tesis.playon.restful.domain.Provincia;

@Repository("paisDao")
public class PaisDao extends HibernateDaoSupport implements IPaisDao {

    @Override
    public void save(Pais pais) {
	getSessionFactory().getCurrentSession().save(pais);
    }

    @Override
    public void update(Pais pais) {
	getSessionFactory().getCurrentSession().update(pais);
    }

    @Override
    public void delete(Pais pais) {
	getSessionFactory().getCurrentSession().delete(pais);
    }

    @Override
    public Pais findByNombrePais(String nombrePais) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais where nombre=?")
		.setParameter(0, nombrePais).list();
	return (Pais) list.get(0);
    }

    @Override
    public List<Pais> findAll() {
	List<Pais> paises = new ArrayList<Pais>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais order by nombre").list();
	for (Object object : list) {
	    paises.add((Pais) object);
	}
	return paises;
    }

    @Override
    public Pais findByPaisId(Integer id) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais where id=?").setParameter(0, id)
		.list();
	return (Pais) list.get(0);
    }

    @Override
    public List<Provincia> getProvincias(Integer idPais) {
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Provincia where pais.id=?")
		.setParameter(0, idPais).list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }
    
}