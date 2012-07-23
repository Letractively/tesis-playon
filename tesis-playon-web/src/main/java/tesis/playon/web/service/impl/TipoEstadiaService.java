package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITipoEstadiaDao;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.ITipoEstadiaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TipoEstadiaService implements ITipoEstadiaService {

    ITipoEstadiaDao tipoEstadiDao;

    @Transactional(readOnly = false)
    @Override
    public void save(TipoEstadia tipoEstadia) {
	getTipoEstadiDao().save(tipoEstadia);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(TipoEstadia tipoEstadia) {
	getTipoEstadiDao().update(tipoEstadia);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(TipoEstadia tipoEstadia) {
	getTipoEstadiDao().delete(tipoEstadia);
    }

    @Override
    public List<TipoEstadia> findAll() {
	return getTipoEstadiDao().findAll();
    }

    @Override
    public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
	return getTipoEstadiDao().findByNombreTipoEstadia(nombreTipoEstadia);
    }

    public ITipoEstadiaDao getTipoEstadiDao() {
	return tipoEstadiDao;
    }

    public void setTipoEstadiDao(ITipoEstadiaDao tipoEstadiDao) {
	this.tipoEstadiDao = tipoEstadiDao;
    }

}