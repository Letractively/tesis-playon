package tesis.playon.restful.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.PerfilPlaya;
import tesis.playon.restful.domain.PerfilesPlayas;
import tesis.playon.restful.service.IPerfilPlayaService;

@Controller("perfilPlayaController")
public class PerfilPlayaController {

    @Resource(name = "perfilPlayaService")
    private IPerfilPlayaService perfilPlayaService;

    @RequestMapping(value = "/perfilesplayas", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    PerfilesPlayas getPerfilesPlayas() {
	PerfilesPlayas result = new PerfilesPlayas();
	result.setLista(perfilPlayaService.findAll());
	return result;
    }

    @RequestMapping(value = "/perfilplaya/{nombreComercialPlaya}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    PerfilPlaya getPerfilPlaya(@PathVariable("nombreComercialPlaya") String nombreComercialPlaya) {
	return perfilPlayaService.findByPlaya(nombreComercialPlaya);
    }
}