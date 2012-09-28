package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Favorito;
import tesis.playon.restful.domain.Playa;

public interface IFavoritoDao {

    void save(Favorito favorito);

    void update(Favorito favorito);

    void delete(Favorito favorito);

    List<Favorito> findAll();

    List<Favorito> findByCliente(Cliente cliente);

    boolean isFavorito(Cliente cliente, Playa playa);
}
