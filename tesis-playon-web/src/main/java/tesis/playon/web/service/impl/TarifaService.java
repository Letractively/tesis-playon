package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.Tarifa;
import tesis.playon.web.service.ITarifaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TarifaService implements ITarifaService {

    @Transactional(readOnly = false)
    @Override
    public void save(Tarifa tarifa) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(Tarifa tarifa) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Tarifa tarifa) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Tarifa> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Tarifa findByTarifaVigente(String tarifaVigente) {
	// TODO Auto-generated method stub
	return null;
    }

}