package tesis.playon.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tesis.playon.web.dao.impl.TipoDocDao;
import tesis.playon.web.dao.impl.UsuarioDao;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;

/**
 * 
 * @author garribere
 * 
 */


@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

    protected static Logger logger = Logger.getLogger("UsuarioController");

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private TipoDocDao tipoDocumentoDao;

    @ModelAttribute("listaUsuarios")
    public List<Usuario> popularUsuarios() {
	return usuarioDao.findAll();
    }
    
    @ModelAttribute("listaTipoDocumentos")
    public List<TipoDoc> popularTipoDocumento() {
	return tipoDocumentoDao.findAll();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getUsuarios(Model model) {
	logger.debug("Recibida peticion para mostrar todos los usuarios");
	List<Usuario> usuarios = usuarioDao.findAll();
	model.addAttribute("usuarios", usuarios);
	return "usuariolist";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
	logger.debug("Recibida peticion para mostrar pagina agregar");
	model.addAttribute("usuarioAtributo", new Usuario());
	List<TipoDoc> tiposDocumentos = tipoDocumentoDao.findAll();
	model.addAttribute("tiposDocumento", tiposDocumentos);
	model.addAttribute("listaTipoDocumentos", tipoDocumentoDao.findAll());
	return "usuarioadd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("usuarioAtributo") Usuario usuario) {
	logger.debug("Recibido pedido para agregar un usuario");
	usuarioDao.save(usuario);
	return "usuarioadded";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getUpdate(@RequestParam(value = "nombre") String nombre, Model model) {
	logger.debug("Recibida la peticion para mostrar la pagina de edicion");
	model.addAttribute("usuarioAtributo", usuarioDao.findByNombreUsuario(nombre));
	model.addAttribute("tiposDocumento", tipoDocumentoDao.findAll());
	return "usuarioupdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute("usuarioAtributo") Usuario usuario, Model model) {
	logger.debug("Recibida la peticion para actualizar un usuario");
	usuarioDao.update(usuario);
	model.addAttribute("id", usuario.getId());
	return "usuarioupdated";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id, Model model) {
	logger.debug("Recibida la peticion para borrar usuario existente");
	Usuario usuario = new Usuario();
	usuario.setID(id);
	usuarioDao.delete(usuario);
	model.addAttribute("id", id);
	return "usuariodeleted";
    }
}