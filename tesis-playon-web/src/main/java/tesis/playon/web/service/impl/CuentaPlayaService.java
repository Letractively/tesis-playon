package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ICuentaPlayaDao;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.service.ICuentaPlayaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class CuentaPlayaService implements ICuentaPlayaService {

    ICuentaPlayaDao cuentaPlayaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(CuentaPlaya CuentaPlaya) {
	getCuentaPlayaDao().save(CuentaPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(CuentaPlaya CuentaPlaya) {
	getCuentaPlayaDao().update(CuentaPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(CuentaPlaya CuentaPlaya) {
	getCuentaPlayaDao().delete(CuentaPlaya);
    }

    @Override
    public List<CuentaPlaya> findAll() {
	return getCuentaPlayaDao().findAll();
    }

    @Override
    public CuentaPlaya findByNroCuentaPlaya(Integer nroCuentaPlaya) {
	return getCuentaPlayaDao().findByNroCuenta(nroCuentaPlaya);
    }

    public ICuentaPlayaDao getCuentaPlayaDao() {
	return cuentaPlayaDao;
    }

    public void setCuentaClienteDao(ICuentaPlayaDao cuentaPlayaDao) {
	this.cuentaPlayaDao = cuentaPlayaDao;
    }

}