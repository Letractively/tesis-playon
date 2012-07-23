package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.ITipoEstadiaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TipoEstadiaService implements ITipoEstadiaService {
    
    @Transactional(readOnly = false)
    @Override
    public void save(TipoEstadia tipoEstadia) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(TipoEstadia tipoEstadia) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(TipoEstadia tipoEstadia) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<TipoEstadia> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
	// TODO Auto-generated method stub
	return null;
    }

}