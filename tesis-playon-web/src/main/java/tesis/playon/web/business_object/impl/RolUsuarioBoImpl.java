package tesis.playon.web.business_object.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tesis.playon.web.business_object.RolUsuarioBo;
import tesis.playon.web.business_object.dao.RolUsuarioDao;
import tesis.playon.web.model.RolUsuario;

@Service("rolUsuarioBo")
public class RolUsuarioBoImpl implements RolUsuarioBo {

    @Autowired
    RolUsuarioDao rolUsuarioDao;

    public void setClienteDao(RolUsuarioDao rolUsuarioDao) {
	this.rolUsuarioDao = rolUsuarioDao;
    }

    public void save(RolUsuario rolUsuario) {
	rolUsuarioDao.save(rolUsuario);
    }

    public void update(RolUsuario rolUsuario) {
	rolUsuarioDao.update(rolUsuario);
    }

    public void delete(RolUsuario rolUsuario) {
	rolUsuarioDao.delete(rolUsuario);
    }

    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	return rolUsuarioDao.findByNombreRolUsuario(nombreRolUsuario);
    }

}
