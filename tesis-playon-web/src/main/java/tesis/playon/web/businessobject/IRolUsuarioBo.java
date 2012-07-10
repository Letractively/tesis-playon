package tesis.playon.web.businessobject;

import java.util.List;

import tesis.playon.web.model.RolUsuario;

public interface IRolUsuarioBo {

    void save(RolUsuario rolUsuario);

    void update(RolUsuario rolUsuario);

    void delete(RolUsuario rolUsuario);

    RolUsuario findByNombreRolUsuario(String nombreRolUsuario);

    List<RolUsuario> findAll();
}
