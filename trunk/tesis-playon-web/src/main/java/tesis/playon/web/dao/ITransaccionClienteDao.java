package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TransaccionCliente;

/**
 * @author Pablo
 * 
 */
public interface ITransaccionClienteDao {

    void save(TransaccionCliente transaccionCliente);

    void update(TransaccionCliente transaccionCliente);

    void delete(TransaccionCliente transaccionCliente);

    TransaccionCliente findByCuentaCliente(CuentaCliente cuentaClienteID);

    List<TransaccionCliente> findAll();

    List<TransaccionCliente> findTransaccionesByCuentaCliente(CuentaCliente cuentaCliente);

}
