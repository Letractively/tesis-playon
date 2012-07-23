package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.Localidad;
import tesis.playon.web.service.ILocalidadService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class LocalidadService implements ILocalidadService {

    @Transactional(readOnly = false)
    @Override
    public void save(Localidad localidad) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(Localidad localidad) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Localidad localidad) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Localidad> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Localidad findByNombreLocalidad(String nombreLocalidad) {
	// TODO Auto-generated method stub
	return null;
    }

}