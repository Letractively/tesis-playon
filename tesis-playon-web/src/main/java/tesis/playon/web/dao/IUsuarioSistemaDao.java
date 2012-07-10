package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.UsuarioSistema;

public interface IUsuarioSistemaDao {

    void save(UsuarioSistema usuarioSistema);

    void update(UsuarioSistema usuarioSistema);

    void delete(UsuarioSistema usuarioSistema);

    UsuarioSistema findByNombreUsuarioSistema(Usuario usuario);
    
    List<UsuarioSistema> findAll();
}
