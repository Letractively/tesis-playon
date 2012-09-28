package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.ICategoriaVehiculoDao;
import tesis.playon.restful.domain.CategoriaVehiculo;

@Repository("categoriaVehiculoDao")
public class CategoriaVehiculoDao extends HibernateDaoSupport implements ICategoriaVehiculoDao {

    @Override
    public void save(CategoriaVehiculo categoria) {
	getSessionFactory().getCurrentSession().save(categoria);
    }

    @Override
    public void update(CategoriaVehiculo categoria) {
	getSessionFactory().getCurrentSession().update(categoria);
    }

    @Override
    public void delete(CategoriaVehiculo categoria) {
	getSessionFactory().getCurrentSession().delete(categoria);
    }

    @Override
    public CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoria) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CategoriaVehiculo where nombre=?")
		.setParameter(0, nombreCategoria).list();
	return (CategoriaVehiculo) list.get(0);
    }

    @Override
    public List<CategoriaVehiculo> findAll() {
	List<CategoriaVehiculo> categorias = new ArrayList<CategoriaVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CategoriaVehiculo order by nombre")
		.list();
	for (Object object : list) {
	    categorias.add((CategoriaVehiculo) object);
	}
	return categorias;
    }
    
}