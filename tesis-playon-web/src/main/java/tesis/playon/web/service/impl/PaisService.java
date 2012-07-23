package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.model.Pais;
import tesis.playon.web.service.IPaisService;

/**
 * 
 * @author gmorales
 * 
 */
public class PaisService implements IPaisService {

    IPaisDao paisDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Pais pais) {
	getPaisDao().save(pais);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Pais pais) {
	getPaisDao().update(pais);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Pais pais) {
	getPaisDao().delete(pais);
    }

    @Override
    public List<Pais> findAll() {
	return getPaisDao().findAll();
    }

    @Override
    public Pais findByNombrPais(String nombrePais) {
	return getPaisDao().findByNombrePais(nombrePais);
    }

    public IPaisDao getPaisDao() {
	return paisDao;
    }

    public void setPaisDao(IPaisDao paisDao) {
	this.paisDao = paisDao;
    }

}