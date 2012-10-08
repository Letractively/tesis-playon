package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.RolUsuario;

public interface IRolUsuarioService {

    void save(RolUsuario rolUsuario);

    void update(RolUsuario rolUsuario);

    void delete(RolUsuario rolUsuario);

    List<RolUsuario> findAll();
    
    RolUsuario findByNombreRolUsuario(String nombreRolUsuario);

}
