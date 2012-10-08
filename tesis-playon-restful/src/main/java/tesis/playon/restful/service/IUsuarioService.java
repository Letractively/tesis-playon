package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.Usuario;

public interface IUsuarioService {

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    Usuario findByNombreUsuario(String usuario);

    List<Usuario> findByPlaya(Playa playa);
    
    Usuario findGerenteByPlaya(Playa playa);
    
    List<Usuario> findAll();

}