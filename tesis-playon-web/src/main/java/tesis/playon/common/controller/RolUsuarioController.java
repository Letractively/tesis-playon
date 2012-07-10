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
@RequestMapping(value = "/registracionRolUsuario")
public class RolUsuarioController {

    protected static Logger logger = Logger.getLogger("RolUsuarioController");

    @Autowired
    private RolUsuarioDao rolUsuarioDao;

    // private RolUsuarioService rolUsuarioService;

    @RequestMapping(value = "/rolesUsuario", method = RequestMethod.GET)
    public String getRolUsuarios(Model model) {
	logger.debug("Recibida peticion para mostrar todos los roles de usuarios");
	List<RolUsuario> rolesUsuario = rolUsuarioDao.findAll();
	model.addAttribute("rolesUsuario", rolesUsuario);
	// Resuelve /WEB-INF/views/personspage.jsp
	return "personspage";
    }

    @RequestMapping(value = "/rolesUsuario/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
	logger.debug("Recibida peticion para mostrar pagina agregar");
	// Create new Person and add to model
	// This is the formBackingOBject
	model.addAttribute("rolUsuarioAtributo", new RolUsuario());
	// Resuelve /WEB-INF/views/addpage.jsp
	return "addpage";
    }

    @RequestMapping(value = "/rolesUsuario/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("rolUsuarioAtributo") RolUsuario rolUsuario) {
	logger.debug("Recibido pedido para agregar un rol de usuario");
	// The "personAttribute" model has been passed to the controller from the JSP
	// We use the name "personAttribute" because the JSP uses that name
	// Call PersonService to do the actual adding
	// rolUsuarioService.add(rolUsuario);
	rolUsuarioDao.save(rolUsuario);
	// resuelve /WEB-INF/views/addedpage.jsp
	return "addedpage";
    }

    @RequestMapping(value = "/rolesUsuario/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) String nombreRol, Model model) {
	logger.debug("Recibida la peticion para borra un rol de usuario existente");
	// Call PersonService to do the actual deleting
	RolUsuario rolUsuario = new RolUsuario(nombreRol);
	rolUsuarioDao.delete(rolUsuario);
	// rolUsuarioService.delete(nombreRol);
	// Add id reference to Model
	model.addAttribute("nombreRol", nombreRol);
	// Resuelve /WEB-INF/views/deletedpage.jsp
	return "deletedpage";
    }

    @RequestMapping(value = "/rolesUsuario/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id", required = true) String nombreRol, Model model) {
	logger.debug("Recibida la peticion para mostrar la pagina de edicion");
	// Retrieve existing Person and add to model
	// This is the formBackingOBject
	// model.addAttribute("rolUsuarioAtributo", rolUsuarioService.get(nombreRol));
	model.addAttribute("rolUsuarioAtributo", rolUsuarioDao.findByNombreRolUsuario(nombreRol));
	// Resuelve /WEB-INF/views/editpage.jsp
	return "editpage";
    }

    @RequestMapping(value = "/rolesUsuario/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("rolUsuarioAtributo") RolUsuario rolUsuario,
	    @RequestParam(value = "id", required = true) String nombreRol, Model model) {
	logger.debug("Received request to update person");
	// The "personAttribute" model has been passed to the controller from the JSP
	// We use the name "personAttribute" because the JSP uses that name
	// We manually assign the id because we disabled it in the JSP page
	// When a field is disabled it will not be included in the ModelAttribute
	rolUsuario.setNombre(nombreRol);
	// Delegate to PersonService for editing
	rolUsuarioDao.save(rolUsuario);
	// rolUsuarioService.edit(rolUsuario);
	// Add id reference to Model
	model.addAttribute("id", nombreRol);
	// This will resolve to /WEB-INF/views/editedpage.jsp
	return "editedpage";
    }

}