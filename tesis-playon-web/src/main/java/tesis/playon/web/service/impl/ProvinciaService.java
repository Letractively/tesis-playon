package tesis.playon.web.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IProvinciaDao;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IProvinciaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class ProvinciaService implements IProvinciaService {

    IProvinciaDao provinciaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Provincia provincia) {
	getProvinciaDao().save(provincia);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Provincia provincia) {
	getProvinciaDao().update(provincia);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Provincia provincia) {
	getProvinciaDao().delete(provincia);
    }

    @Override
    public List<Provincia> findAll() {
	return getProvinciaDao().findAll();
    }

    @Override
    public Provincia findByNombreProvincia(String nombreProvincia) {
	return getProvinciaDao().findByNombreProvincia(nombreProvincia);
    }
    
    @Override
    public Set<Localidad> findLocalidades(Provincia provincia){
	return getProvinciaDao().findLocalidades(provincia);
    }

    public IProvinciaDao getProvinciaDao() {
	return provinciaDao;
    }

    public void setProvinciaDao(IProvinciaDao provinciaDao) {
	this.provinciaDao = provinciaDao;
    }

}