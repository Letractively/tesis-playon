package tesis.playon.restful.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.PerfilPlaya;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.IPerfilPlayaService;

@Controller("perfilPlayaController")
public class PerfilPlayaController {

    @Resource(name = "perfilPlayaService")
    private IPerfilPlayaService perfilPlayaService;

    @RequestMapping(value = "/perfilesplayas", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<PerfilPlaya> getPerfilesPlayas() {
	List<PerfilPlaya> result = new ArrayList<PerfilPlaya>();
	result = perfilPlayaService.findAll();
	return result;
    }

    @RequestMapping(value = "/perfilplaya/{playa}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    PerfilPlaya getPerfilPlaya(@PathVariable("playa") Playa playa) {
	return perfilPlayaService.findByPlaya(playa);
    }
}