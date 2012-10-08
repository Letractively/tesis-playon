package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.domain.UsuarioSistema;

public interface IUsuarioSistemaService {

    void save(UsuarioSistema usuarioSistema);

    void update(UsuarioSistema usuarioSistema);

    void delete(UsuarioSistema usuarioSistema);

    UsuarioSistema findByNombreUsuarioSistema(Usuario usuario);

    List<UsuarioSistema> findAll();

}
