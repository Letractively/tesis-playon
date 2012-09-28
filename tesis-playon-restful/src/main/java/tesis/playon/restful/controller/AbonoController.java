package tesis.playon.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tesis.playon.restful.domain.Abono;
import tesis.playon.restful.service.IAbonoService;

@Controller
public class AbonoController {

    @Autowired
    private IAbonoService abonoService;

    @RequestMapping(value = "/abonos", method = RequestMethod.GET)
    public ModelAndView findAll() {
	List<Abono> abono = abonoService.findAll();
	return new ModelAndView("abonoXmlView", BindingResult.MODEL_KEY_PREFIX + "abono", abono);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPerson(@RequestBody Abono abono) {
	abonoService.save(abono);
	return new ModelAndView("abonoXmlView", BindingResult.MODEL_KEY_PREFIX + "abono", "ok");
    }
}