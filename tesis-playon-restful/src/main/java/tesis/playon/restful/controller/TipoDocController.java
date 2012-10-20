package tesis.playon.restful.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.TipoDoc;
import tesis.playon.restful.service.ITipoDocService;

@Controller("tipoDocController")
public class TipoDocController {

    @Resource(name="tipoDocService")
    private ITipoDocService tipoDocService;

    @RequestMapping(value = "/tipodocumentos", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<TipoDoc> getTipoDoc() {
	List<TipoDoc> result = new ArrayList<TipoDoc>();
	result = tipoDocService.findAll();
	return result;
    }
}