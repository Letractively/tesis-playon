package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.service.ICuentaClienteService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class CuentaClienteService implements ICuentaClienteService {

    @Transactional(readOnly = false)
    @Override
    public void save(CuentaCliente cuentaCliente) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void update(CuentaCliente cuentaCliente) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(CuentaCliente cuentaCliente) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<CuentaCliente> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public CuentaCliente findByNroCuentaCliente(String nroCuentaCliente) {
	// TODO Auto-generated method stub
	return null;
    }

}