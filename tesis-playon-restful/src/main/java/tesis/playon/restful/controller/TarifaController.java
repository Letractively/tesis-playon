package tesis.playon.restful.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.Tarifas;
import tesis.playon.restful.service.ITarifaService;

@Controller("tarifaController")
public class TarifaController {

    @Resource(name = "tarifaService")
    private ITarifaService tarifaService;

    @RequestMapping(value = "/tarifas", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Tarifas getTarifas() {
	Tarifas result = new Tarifas();
	result.setLista(tarifaService.findAll());
	return result;
    }

    @RequestMapping(value = "/tarifas/{nombreComercialPlaya}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Tarifas getPlayaNombre(@PathVariable("nombreComercialPlaya") String nombreComercialPlaya) {
	Tarifas result = new Tarifas();
	result.setLista(tarifaService.findTarifaVigenteByPlaya(nombreComercialPlaya));
	return result;
    }

}