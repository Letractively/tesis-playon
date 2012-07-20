package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Usuario;

public interface IUsuarioService {

    void save(Usuario usuario, String nombreTipoDoc);

    void update(Usuario usuario, String nombreTipoDoc);

    void delete(Usuario usuario);

    List<Usuario> findAll();

    Usuario findByNombreUsuario(String nombreUsuario);

}