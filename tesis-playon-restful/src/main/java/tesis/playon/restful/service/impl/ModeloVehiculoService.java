package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.MarcaVehiculo;
import tesis.playon.restful.domain.ModeloVehiculo;
import tesis.playon.restful.service.IModeloVehiculoService;

@Service("modeloVehiculoService")
@Transactional
public class ModeloVehiculoService implements IModeloVehiculoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(ModeloVehiculo modelo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(modelo);
    }

    @Override
    public void update(ModeloVehiculo modelo) {
	Session session = sessionFactory.getCurrentSession();
	session.update(modelo);
    }

    @Override
    public void delete(ModeloVehiculo modelo) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(modelo);
    }

    @Override
    public ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session
		.createQuery("from ModeloVehiculo where nombre=? ORDER BY nombre").setParameter(0, nombreModelo).list();
	return (ModeloVehiculo) list.get(0);
    }

    @Override
    public List<ModeloVehiculo> findByMarca(MarcaVehiculo marca) {
	Session session = sessionFactory.getCurrentSession();
	List<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
	List<?> list = session
		.createQuery("from ModeloVehiculo where marcaVehiculo=? ORDER BY nombre").setParameter(0, marca).list();
	for (Object object : list) {
	    modelos.add((ModeloVehiculo) object);
	}
	return modelos;
    }

    @Override
    public List<ModeloVehiculo> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
	List<?> list = session.createQuery("from ModeloVehiculo ORDER BY nombre")
		.list();
	for (Object object : list) {
	    modelos.add((ModeloVehiculo) object);
	}
	return modelos;
    }
}