package tesis.playon.restful.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.EstadoPlaya;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.IPlayaService;

@Controller("playaController")
public class PlayaController {

    @Resource(name = "playaService")
    private IPlayaService playaService;

    @RequestMapping(value = "/playas", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Playa> getPlayas() {
	List<Playa> result = new ArrayList<Playa>();
	result = playaService.findAll();
	return result;
    }

    @RequestMapping(value = "/playas/{estado}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<Playa> getPlayas(@PathVariable("estado") EstadoPlaya estado) {
	List<Playa> result = new ArrayList<Playa>();
	result = playaService.findAll();
	return result;
    }

    @RequestMapping(value = "/playa/{nombreComercial}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Playa getPlayaNombre(@PathVariable("nombreComercial") String nombreComercial) {
	return playaService.findByNombreComercial(nombreComercial);
    }
}