package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.IModeloVehiculoDao;
import tesis.playon.restful.domain.MarcaVehiculo;
import tesis.playon.restful.domain.ModeloVehiculo;

public class ModeloVehiculoDao extends HibernateDaoSupport implements IModeloVehiculoDao {

    @Override
    public void save(ModeloVehiculo modelo) {
	getSessionFactory().getCurrentSession().save(modelo);
    }

    @Override
    public void update(ModeloVehiculo modelo) {
	getSessionFactory().getCurrentSession().update(modelo);
    }

    @Override
    public void delete(ModeloVehiculo modelo) {
	getSessionFactory().getCurrentSession().delete(modelo);
    }

    @Override
    public ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from ModeloVehiculo where nombre=? ORDER BY nombre").setParameter(0, nombreModelo).list();
	return (ModeloVehiculo) list.get(0);
    }

    @Override
    public List<ModeloVehiculo> findByMarca(MarcaVehiculo marca) {
	List<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from ModeloVehiculo where marcaVehiculo=? ORDER BY nombre").setParameter(0, marca).list();
	for (Object object : list) {
	    modelos.add((ModeloVehiculo) object);
	}
	return modelos;
    }

    @Override
    public List<ModeloVehiculo> findAll() {
	List<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from ModeloVehiculo ORDER BY nombre")
		.list();
	for (Object object : list) {
	    modelos.add((ModeloVehiculo) object);
	}
	return modelos;
    }
    
}