package tesis.playon.restful.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.service.IUsuarioService;

@Controller("usuarioController")
public class UsuarioController {

    @Resource(name = "usuarioService")
    private IUsuarioService usuarioService;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<Usuario> getUsuario() {
	List<Usuario> result = new ArrayList<Usuario>();
	result = usuarioService.findAll();
	return result;
    }

    @RequestMapping(value = "/usuario/{usuario}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    Usuario getUsuario(@PathVariable("usuario") String usuario) {
	return usuarioService.findByNombreUsuario(usuario);
    }
}