/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.TransaccionCliente;

/**
 * @author gmorales
 * 
 */
public interface ITransaccionClienteService {

    public void save(TransaccionCliente transaccionCliente);

    public void update(TransaccionCliente transaccionCliente);

    public void delete(TransaccionCliente transaccionCliente);

    public TransaccionCliente findByCuentaCliente(String cuentaClienteID);

    public List<TransaccionCliente> findAll();
    
}