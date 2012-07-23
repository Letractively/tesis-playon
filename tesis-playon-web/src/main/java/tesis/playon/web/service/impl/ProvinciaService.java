package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IProvinciaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class ProvinciaService implements IProvinciaService {

    @Transactional(readOnly = false)
    @Override
    public void save(Provincia provincia) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(Provincia provincia) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Provincia provincia) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Provincia> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Provincia findByNombreProvincia(String nombreProvincia) {
	// TODO Auto-generated method stub
	return null;
    }

}