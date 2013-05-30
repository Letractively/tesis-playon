package tesis.playon.restful.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.Comentarios;
import tesis.playon.restful.service.IComentarioService;

@Controller("comentarioController")
public class ComentarioController {

    @Resource(name = "comentarioService")
    private IComentarioService comentarioService;

    @RequestMapping(value = "/comentarios", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Comentarios getTarifas() {
	Comentarios result = new Comentarios();
	result.setLista(comentarioService.findAll());
	return result;
    }

    @RequestMapping(value = "/comentarios/{nombreComercialPlaya}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Comentarios getPlayaNombre(@PathVariable("nombreComercialPlaya") String nombreComercialPlaya) {
	Comentarios result = new Comentarios();
	result.setLista(comentarioService.findByPlaya(nombreComercialPlaya));
	return result;
    }

}