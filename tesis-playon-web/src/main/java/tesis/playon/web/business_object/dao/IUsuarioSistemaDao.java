package tesis.playon.web.business_object.dao;

import java.util.List;

import tesis.playon.web.model.UsuarioSistema;

public interface IUsuarioSistemaDao {

    void save(UsuarioSistema usuarioSistema);

    void update(UsuarioSistema usuarioSistema);

    void delete(UsuarioSistema usuarioSistema);

    UsuarioSistema findByNombreUsuarioSistema(String usuarioSistemaID);
    
    List<UsuarioSistema> findAll();
}
