package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.ColorVehiculo;
import tesis.playon.restful.service.IColorVehiculoService;

@Service("colorVehiculoService")
@Transactional
public class ColorVehiculoService implements IColorVehiculoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(ColorVehiculo color) {
	Session session = sessionFactory.getCurrentSession();
	session.save(color);
    }

    @Override
    public void update(ColorVehiculo color) {
	Session session = sessionFactory.getCurrentSession();
	session.update(color);
    }

    @Override
    public void delete(ColorVehiculo color) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(color);
    }

    @Override
    public ColorVehiculo findByNombreColorVehiculo(String colorVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session
		.createQuery("from ColorVehiculo where nombre=? order by nombre").setParameter(0, colorVehiculo).list();
	return (ColorVehiculo) list.get(0);
    }

    @Override
    public List<ColorVehiculo> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<ColorVehiculo> colores = new ArrayList<ColorVehiculo>();
	List<?> list = session.createQuery("from ColorVehiculo order by nombre").list();
	for (Object object : list) {
	    colores.add((ColorVehiculo) object);
	}
	return colores;
    }
}