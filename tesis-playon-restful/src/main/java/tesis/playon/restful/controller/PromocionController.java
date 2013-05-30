package tesis.playon.restful.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.Promociones;
import tesis.playon.restful.service.IPromocionService;

@Controller("promocionController")
public class PromocionController {

    @Resource(name = "promocionService")
    private IPromocionService promocionService;

    @RequestMapping(value = "/promociones", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Promociones getPromociones() {
	Promociones result = new Promociones();
	result.setLista(promocionService.findAll());
	return result;
    }

    @RequestMapping(value = "/promociones/{nombreComercialPlaya}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Promociones getPromocionesVigentesByPlaya(@PathVariable("nombreComercialPlaya") String nombreComercialPlaya) {
	Promociones result = new Promociones();
	result.setLista(promocionService.findPromocionVigenteByPlaya(nombreComercialPlaya));
	return result;
    }

}