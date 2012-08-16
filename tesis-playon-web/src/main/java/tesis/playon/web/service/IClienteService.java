/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Cliente;

/**
 * @author pablo
 *
 */
public interface IClienteService {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByNumeroCliente(Integer numeroCliente);
    
    List<Cliente> findAll();
    
    Cliente findByNombreUsuario(String nombreUser);
}
