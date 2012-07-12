package tesis.playon.common.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tesis.playon.web.dao.impl.RolUsuarioDao;
import tesis.playon.web.model.RolUsuario;

/**
 * 
 * @author gmorales
 * 
 */
@Controller
@RequestMapping(value = "/roles-usuario")
public class RolUsuarioController {

    protected static Logger logger = Logger.getLogger("RolUsuarioController");

    @Autowired
    private RolUsuarioDao rolUsuarioDao;

    @RequestMapping(method = RequestMethod.GET)
    public String getRolUsuarios(Model model) {
	logger.debug("Recibida peticion para mostrar todos los roles de usuarios");
	List<RolUsuario> rolesUsuario = rolUsuarioDao.findAll();
	model.addAttribute("rolesUsuario", rolesUsuario);
	return "rolusuariolist";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
	logger.debug("Recibida peticion para mostrar pagina agregar");
	model.addAttribute("rolUsuarioAtributo", new RolUsuario());
	return "rolusuarioadd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("rolUsuarioAtributo") RolUsuario rolUsuario) {
	logger.debug("Recibido pedido para agregar un rol de usuario");
	rolUsuarioDao.save(rolUsuario);
	return "rolusuarioadded";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getUpdate(@RequestParam(value = "nombre") String nombre, Model model) {
	logger.debug("Recibida la peticion para mostrar la pagina de edicion");
	model.addAttribute("rolUsuarioAtributo", rolUsuarioDao.findByNombreRolUsuario(nombre));
	return "rolusuarioupdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute("rolUsuarioAtributo") RolUsuario rolUsuario,
	    @RequestParam(value = "nombre") String nombre, @RequestParam(value = "descripcion") String descripcion,
	    Model model) {
	logger.debug("Recibida la peticion para actualiza un rol de usuario");
	rolUsuario.setNombre(nombre);
	rolUsuario.setDescripcion(descripcion);
	rolUsuarioDao.update(rolUsuario);
	model.addAttribute("id", rolUsuario.getId());
	return "rolusuarioupdated";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id, Model model) {
	logger.debug("Recibida la peticion para borra un rol de usuario existente");
	RolUsuario rolUsuario = new RolUsuario();
	rolUsuario.setId(id);
	rolUsuarioDao.delete(rolUsuario);
	model.addAttribute("id", id);
	return "rolusuariodeleted";
    }

}