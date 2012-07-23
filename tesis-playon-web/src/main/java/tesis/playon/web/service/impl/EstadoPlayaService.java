package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.service.IEstadoPlayaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class EstadoPlayaService implements IEstadoPlayaService {

    @Transactional(readOnly = false)
    @Override
    public void save(EstadoPlaya EstadoPlaya) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(EstadoPlaya EstadoPlaya) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(EstadoPlaya EstadoPlaya) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<EstadoPlaya> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public EstadoPlaya findByNombreEstadoPlaya(String nombreEstadoPlaya) {
	// TODO Auto-generated method stub
	return null;
    }

}