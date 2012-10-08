package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Usuario;

public interface IClienteService {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByNumeroCliente(Integer numeroCliente);

    Cliente findByIdUsuario(Usuario usuario);

    List<Cliente> findAll();
}
