package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IVehiculoDao;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IVehiculoService;
/**
 * 
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class VehiculoService implements IVehiculoService {

    IVehiculoDao vehiculoDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Vehiculo vehiculo) {
	// TODO Auto-generated method stub
	getVehiculoDao().save(vehiculo);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Vehiculo vehiculo) {
	// TODO Auto-generated method stub
	getVehiculoDao().update(vehiculo);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Vehiculo vehiculo) {
	// TODO Auto-generated method stub
	getVehiculoDao().delete(vehiculo);
    }

    @Override
    public Vehiculo findByPatenteVehiculo(String patenteVehiculo) {
	// TODO Auto-generated method stub
	return getVehiculoDao().findByPatenteVehiculo(patenteVehiculo);
    }

    @Override
    public List<Vehiculo> findAll() {
	// TODO Auto-generated method stub
	return getVehiculoDao().findAll();
    }

    public IVehiculoDao getVehiculoDao() {
        return vehiculoDao;
    }

    public void setVehiculoDao(IVehiculoDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }

}
