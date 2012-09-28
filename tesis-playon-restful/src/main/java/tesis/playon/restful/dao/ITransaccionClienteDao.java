package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.TransaccionCliente;

public interface ITransaccionClienteDao {

    void save(TransaccionCliente transaccionCliente);

    void update(TransaccionCliente transaccionCliente);

    void delete(TransaccionCliente transaccionCliente);

    TransaccionCliente findByCuentaCliente(String cuentaClienteID);

    List<TransaccionCliente> findAll();
    
}
