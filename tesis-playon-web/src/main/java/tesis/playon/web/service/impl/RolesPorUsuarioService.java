package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IRolesPorUsuarioDao;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.service.IRolesPorUsuarioService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class RolesPorUsuarioService implements IRolesPorUsuarioService {

    IRolesPorUsuarioDao rolPorUserDao;

    @Transactional(readOnly = false)
    @Override
    public void save(RolesPorUsuario rolPorUser) {
	getRolesPorUsuarioDao().save(rolPorUser);
    }

    private IRolesPorUsuarioDao getRolesPorUsuarioDao() {

	return rolPorUserDao;
    }

    @Transactional(readOnly = false)
    @Override
    public void update(RolesPorUsuario rolPorUser) {
	getRolesPorUsuarioDao().update(rolPorUser);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(RolesPorUsuario rolPorUser) {
	getRolesPorUsuarioDao().delete(rolPorUser);
    }

    @Override
    public List<RolesPorUsuario> findAll() {
	return getRolesPorUsuarioDao().findAll();
    }

    @Override
    public RolesPorUsuario findByNombreRolUsuario(String nombreUser) {
	return getRolesPorUsuarioDao().findByNombreRolUsuario(nombreUser);
    }

    public void setRolesPorUsuarioDao(IRolesPorUsuarioDao rolPorUserDao) {
	this.rolPorUserDao = rolPorUserDao;
    }

}