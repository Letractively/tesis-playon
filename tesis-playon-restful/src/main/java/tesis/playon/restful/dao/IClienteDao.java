package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Usuario;

public interface IClienteDao {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByNumeroCliente(Integer numeroCliente);

    Cliente findByNombreUsuario(String nombreUser);

    Cliente findByIdUsuario(Usuario usuario);

    List<Cliente> findAll();
}
