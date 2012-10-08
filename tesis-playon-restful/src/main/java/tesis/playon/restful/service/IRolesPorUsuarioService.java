package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.RolesPorUsuario;

public interface IRolesPorUsuarioService {

    void save(RolesPorUsuario rolesPorUsuario);

    void update(RolesPorUsuario rolesPorUsuario);

    void delete(RolesPorUsuario rolesPorUsuario);

    RolesPorUsuario findByNombreUsuario(String nombreUsuario);

    List<RolesPorUsuario> findAll();
}
