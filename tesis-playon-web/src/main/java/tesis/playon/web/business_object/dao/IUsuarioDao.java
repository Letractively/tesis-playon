package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.Usuario;

public interface IUsuarioDao {

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    Usuario findByNombreUsuario(String usuario);
    
    List<Usuario> findAll();

}
