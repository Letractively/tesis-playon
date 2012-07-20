package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Usuario;

public interface IUsuarioDao {

    void save(Usuario usuario, String nombreTipoDoc);

    void update(Usuario usuario, String nombreTipoDoc);

    void delete(Usuario usuario);

    Usuario findByNombreUsuario(String usuario);

    List<Usuario> findAll();

}
