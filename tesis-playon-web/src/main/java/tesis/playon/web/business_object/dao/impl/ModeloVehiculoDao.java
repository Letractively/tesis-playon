/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IModeloVehiculoDao;
import tesis.playon.web.model.ModeloVehiculo;

/**
 * @author Pablo
 *
 */
@Repository("modeloVehiculoDao")
public class ModeloVehiculoDao extends CustomHibernateDaoSupport implements IModeloVehiculoDao{
    
    public void save(ModeloVehiculo modelo) {
	getHibernateTemplate().save(modelo);
    }

    public void update(ModeloVehiculo modelo) {
	getHibernateTemplate().update(modelo);
    }

    public void delete(ModeloVehiculo modelo) {
	getHibernateTemplate().delete(modelo);
    }

    public ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo) {
	List<?> list = getHibernateTemplate().find("from ModeloVehiculo where nombre=?", nombreModelo);
	return (ModeloVehiculo) list.get(0);
    }
    
    public List<ModeloVehiculo> findAll(){
	List<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
	List<?> list = getHibernateTemplate().find("from ModeloVehiculo");
	for (Object object : list) {
	    modelos.add((ModeloVehiculo)object);
	}
	return modelos;
    }
}
