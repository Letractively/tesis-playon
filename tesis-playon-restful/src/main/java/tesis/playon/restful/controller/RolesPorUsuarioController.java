package tesis.playon.restful.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.RolesPorUsuario;
import tesis.playon.restful.service.IRolesPorUsuarioService;

@Controller("rolesPorUsuarioController")
public class RolesPorUsuarioController {

    @Resource(name="rolesPorUsuarioService")
    private IRolesPorUsuarioService rolesPorUsuarioService;

    @RequestMapping(value = "/rolesporusuarios", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<RolesPorUsuario> getRolesPorUsuario() {
	List<RolesPorUsuario> result = new ArrayList<RolesPorUsuario>();
	result = rolesPorUsuarioService.findAll();
	return result;
    }
    
    @RequestMapping(value = "/rolesporusuario/{nombreUsuario}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    RolesPorUsuario getRolesPorUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
	return rolesPorUsuarioService.findByNombreUsuario(nombreUsuario);
    }
}