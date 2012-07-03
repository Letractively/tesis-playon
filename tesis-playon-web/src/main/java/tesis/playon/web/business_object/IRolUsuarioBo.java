package tesis.playon.web.business_object;

import tesis.playon.web.model.RolUsuario;

public interface IRolUsuarioBo {

    void save(RolUsuario rolUsuario);

    void update(RolUsuario rolUsuario);

    void delete(RolUsuario rolUsuario);

    RolUsuario findByNombreRolUsuario(String nombreRolUsuario);

}
