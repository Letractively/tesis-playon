package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.Pais;
import tesis.playon.web.service.IPaisService;

/**
 * 
 * @author gmorales
 * 
 */
public class PaisService implements IPaisService {

    @Transactional(readOnly = false)
    @Override
    public void save(Pais pais) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(Pais pais) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Pais pais) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Pais> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Pais findByNombrPais(String nombrePais) {
	// TODO Auto-generated method stub
	return null;
    }

}