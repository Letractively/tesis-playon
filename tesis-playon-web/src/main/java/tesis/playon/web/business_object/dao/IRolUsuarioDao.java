package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.RolUsuario;

public interface IRolUsuarioDao {

    void save(RolUsuario rolUsuario);

    void update(RolUsuario rolUsuario);

    void delete(RolUsuario rolUsuario);

    RolUsuario findByNombreRolUsuario(String nombreRolUsuario);

}
