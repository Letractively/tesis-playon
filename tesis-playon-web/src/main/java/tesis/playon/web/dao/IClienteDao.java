package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Cliente;

/**
 * @author Pablo
 *
 */
public interface IClienteDao {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByNumeroCliente(Integer numeroCliente);
    
    Cliente findByNombreUsuario(String nombreUser);
    
    List<Cliente> findAll();
}
