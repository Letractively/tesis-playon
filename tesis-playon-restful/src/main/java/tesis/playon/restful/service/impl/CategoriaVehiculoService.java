package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.CategoriaVehiculo;
import tesis.playon.restful.service.ICategoriaVehiculoService;

@Service("categoriaVehiculoService")
public class CategoriaVehiculoService implements ICategoriaVehiculoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(CategoriaVehiculo categoria) {
	Session session = sessionFactory.getCurrentSession();
	session.save(categoria);
    }

    @Override
    public void update(CategoriaVehiculo categoria) {
	Session session = sessionFactory.getCurrentSession();
	session.update(categoria);
    }

    @Override
    public void delete(CategoriaVehiculo categoria) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(categoria);
    }

    @Override
    public CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoria) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from CategoriaVehiculo where nombre=?")
		.setParameter(0, nombreCategoria).list();
	return (CategoriaVehiculo) list.get(0);
    }

    @Override
    public List<CategoriaVehiculo> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<CategoriaVehiculo> categorias = new ArrayList<CategoriaVehiculo>();
	List<?> list = session.createQuery("from CategoriaVehiculo order by nombre")
		.list();
	for (Object object : list) {
	    categorias.add((CategoriaVehiculo) object);
	}
	return categorias;
    }
}