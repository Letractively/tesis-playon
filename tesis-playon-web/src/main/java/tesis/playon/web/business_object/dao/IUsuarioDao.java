package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Usuario;

public interface IUsuarioDao {

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    Usuario findByNombreUsuario(String usuario);

}
