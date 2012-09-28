package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IProvinciaDao;
import tesis.playon.restful.domain.Pais;
import tesis.playon.restful.domain.Provincia;

@Repository("provinciaDao")
public class ProvinciaDao extends HibernateDaoSupport implements IProvinciaDao {

    @Override
    public void save(Provincia provincia) {
	getSessionFactory().getCurrentSession().save(provincia);
    }

    @Override
    public void update(Provincia cliente) {
	getSessionFactory().getCurrentSession().update(cliente);
    }

    @Override
    public void delete(Provincia cliente) {
	getSessionFactory().getCurrentSession().delete(cliente);
    }

    @Override
    public Provincia findByNombreProvincia(String nombreProvincia) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Provincia where nombre=?")
		.setParameter(0, nombreProvincia).list();
	return (Provincia) list.get(0);
    }

    @Override
    public List<Provincia> findAll() {
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Provincia order by nombre").list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }

    @Override
    public Provincia findByProvinciaId(Integer id) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Provincia where id=?")
		.setParameter(0, id).list();
	return (Provincia) list.get(0);
    }

    @Override
    public List<Provincia> findProvincias(Pais pais) {
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Provincia where pais=?")
		.setParameter(0, pais).list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }
    
}