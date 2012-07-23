package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.service.ICategoriaVehiculoService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class CategoriaVehiculoService implements ICategoriaVehiculoService {

    @Transactional(readOnly = false)
    @Override
    public void save(CategoriaVehiculo categoriaVehiculo) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(CategoriaVehiculo categoriaVehiculo) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(CategoriaVehiculo categoriaVehiculo) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<CategoriaVehiculo> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoriaVehiculo) {
	// TODO Auto-generated method stub
	return null;
    }

}