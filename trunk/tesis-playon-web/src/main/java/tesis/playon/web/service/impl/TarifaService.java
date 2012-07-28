package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITarifaDao;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.service.ITarifaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TarifaService implements ITarifaService {

    ITarifaDao tarifaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Tarifa tarifa) {
	getTarifaDao().save(tarifa);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Tarifa tarifa) {
	getTarifaDao().update(tarifa);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Tarifa tarifa) {
	getTarifaDao().delete(tarifa);
    }

    @Override
    public List<Tarifa> findAll() {
	return getTarifaDao().findAll();
    }

    public ITarifaDao getTarifaDao() {
	return tarifaDao;
    }

    public void setTarifaDao(ITarifaDao tarifaDao) {
	this.tarifaDao = tarifaDao;
    }

}