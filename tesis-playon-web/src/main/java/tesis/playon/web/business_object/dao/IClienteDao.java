/**
 * 
 */
package tesis.playon.web.business_object.dao;

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

    Cliente findByNumeroCliente(String numeroCliente);
    
    List<Cliente> findAll();
}
