package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.CuentaCliente;

public interface ICuentaClienteService {

    void save(CuentaCliente cuentaCliente);
    
    void update(CuentaCliente cuentaCliente);
    
    void delete(CuentaCliente cuentaCliente);
    
    CuentaCliente findByNroCuenta(Integer nroCuenta);
    
    List<CuentaCliente> findAll();
    
}
