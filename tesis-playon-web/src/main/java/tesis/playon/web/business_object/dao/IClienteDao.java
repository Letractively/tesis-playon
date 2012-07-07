/**
 * 
 */
package tesis.playon.web.business_object.dao;

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
}
