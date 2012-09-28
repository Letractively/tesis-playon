package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IColorVehiculoDao;
import tesis.playon.restful.domain.ColorVehiculo;

@Repository("colorVehiculoDao")
public class ColorVehiculoDao extends HibernateDaoSupport implements IColorVehiculoDao {

    @Override
    public void save(ColorVehiculo color) {
	getSessionFactory().getCurrentSession().save(color);
    }

    @Override
    public void update(ColorVehiculo color) {
	getSessionFactory().getCurrentSession().update(color);
    }

    @Override
    public void delete(ColorVehiculo color) {
	getSessionFactory().getCurrentSession().delete(color);
    }

    @Override
    public ColorVehiculo findByNombreColorVehiculo(String colorVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from ColorVehiculo where nombre=? order by nombre").setParameter(0, colorVehiculo).list();
	return (ColorVehiculo) list.get(0);
    }

    @Override
    public List<ColorVehiculo> findAll() {
	List<ColorVehiculo> colores = new ArrayList<ColorVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from ColorVehiculo order by nombre").list();
	for (Object object : list) {
	    colores.add((ColorVehiculo) object);
	}
	return colores;
    }

}