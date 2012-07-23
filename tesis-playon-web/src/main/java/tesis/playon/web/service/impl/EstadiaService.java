package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.Estadia;
import tesis.playon.web.service.IEstadiaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class EstadiaService implements IEstadiaService {

    @Transactional(readOnly = false)
    @Override
    public void save(Estadia estadia) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(Estadia estadia) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Estadia estadia) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Estadia> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

}