package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.MarcaVehiculo;
import tesis.playon.restful.service.IMarcaVehiculoService;

@Service("marcaVehiculoService")
public class MarcaVehiculoService implements IMarcaVehiculoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(MarcaVehiculo marcaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(marcaVehiculo);
    }

    @Override
    public void update(MarcaVehiculo marcaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.update(marcaVehiculo);
    }

    @Override
    public void delete(MarcaVehiculo marcaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(marcaVehiculo);
    }

    @Override
    public MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session
		.createQuery("from MarcaVehiculo where nombre=? order by nombre asc")
		.setParameter(0, nombreMarcaVehiculo).list();
	return (MarcaVehiculo) list.get(0);
    }

    @Override
    public List<MarcaVehiculo> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<MarcaVehiculo> marcas = new ArrayList<MarcaVehiculo>();
	List<?> list = session
		.createQuery("from MarcaVehiculo marca order by marca.nombre").list();
	for (Object object : list) {
	    marcas.add((MarcaVehiculo) object);
	}
	return marcas;
    }

}