package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IMarcaVehiculoDao;
import tesis.playon.restful.domain.MarcaVehiculo;

@Repository("marcaVehiculoDao")
public class MarcaVehiculoDao extends HibernateDaoSupport implements IMarcaVehiculoDao {

    @Override
    public void save(MarcaVehiculo marcaVehiculo) {
	getSessionFactory().getCurrentSession().save(marcaVehiculo);
    }

    @Override
    public void update(MarcaVehiculo marcaVehiculo) {
	getSessionFactory().getCurrentSession().update(marcaVehiculo);
    }

    @Override
    public void delete(MarcaVehiculo marcaVehiculo) {
	getSessionFactory().getCurrentSession().delete(marcaVehiculo);
    }

    @Override
    public MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from MarcaVehiculo where nombre=? order by nombre asc")
		.setParameter(0, nombreMarcaVehiculo).list();
	return (MarcaVehiculo) list.get(0);
    }

    @Override
    public List<MarcaVehiculo> findAll() {
	List<MarcaVehiculo> marcas = new ArrayList<MarcaVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from MarcaVehiculo marca order by marca.nombre").list();
	for (Object object : list) {
	    marcas.add((MarcaVehiculo) object);
	}
	return marcas;
    }

}