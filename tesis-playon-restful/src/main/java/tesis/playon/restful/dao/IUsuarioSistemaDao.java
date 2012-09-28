package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.domain.UsuarioSistema;

public interface IUsuarioSistemaDao {

    void save(UsuarioSistema usuarioSistema);

    void update(UsuarioSistema usuarioSistema);

    void delete(UsuarioSistema usuarioSistema);

    UsuarioSistema findByNombreUsuarioSistema(Usuario usuario);

    List<UsuarioSistema> findAll();

}
