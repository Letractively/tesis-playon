package tesis.playon.web.businessobject.impl;

import java.util.List;

import org.apache.log4j.Logger;

import tesis.playon.web.businessobject.IRolUsuarioBo;
import tesis.playon.web.dao.IRolUsuarioDao;
import tesis.playon.web.model.RolUsuario;

/**
 * 
 * @author gmorales
 * 
 */
// @Service("rolUsuarioBo")
// @Transactional
public class RolUsuarioBo implements IRolUsuarioBo {

    protected static Logger logger = Logger.getLogger("RolUsuarioBo");

    // @Resource(name = "sessionFactory")
    // private SessionFactory sessionFactory;

    private IRolUsuarioDao rolUsuarioDao;

    public void setClienteDao(IRolUsuarioDao rolUsuarioDao) {
	this.rolUsuarioDao = rolUsuarioDao;
    }

    public void save(RolUsuario rolUsuario) {
	logger.debug("Save rol usuario");
	// Session session = sessionFactory.getCurrentSession();
	rolUsuarioDao.save(rolUsuario);
    }

    public void update(RolUsuario rolUsuario) {
	logger.debug("Update rol usuario");
	rolUsuarioDao.update(rolUsuario);
    }

    public void delete(RolUsuario rolUsuario) {
	logger.debug("Delete rol usuario");
	rolUsuarioDao.delete(rolUsuario);
    }

    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	logger.debug("Find by nombre rol usuario");
	return rolUsuarioDao.findByNombreRolUsuario(nombreRolUsuario);
    }

    public List<RolUsuario> findAll() {
	logger.debug("Find all rol usuarios");
	return rolUsuarioDao.findAll();
    }

}
