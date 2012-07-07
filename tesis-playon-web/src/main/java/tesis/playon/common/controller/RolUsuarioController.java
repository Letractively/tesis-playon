package tesis.playon.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tesis.playon.common.validator.RolUsuarioValidator;
import tesis.playon.web.business_object.dao.impl.RolUsuarioDao;
import tesis.playon.web.model.RolUsuario;

@Controller
@RequestMapping(value = "/registracionRolUsuario")
public class RolUsuarioController {

    private RolUsuarioValidator validator = null;
    private RolUsuarioDao rolUsuarioDao = null;

    @Autowired
    public void setRolUsuarioDao(RolUsuarioDao rolUsuarioDao) {
	this.rolUsuarioDao = rolUsuarioDao;
    }

    public RolUsuarioValidator getValidator() {
	return validator;
    }

    @Autowired
    public void setValidator(RolUsuarioValidator validator) {
	this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap model) {
	List<RolUsuario> listaRolUsuarios = rolUsuarioDao.findAll();
	model.addAttribute("listaRolUsuarios", listaRolUsuarios);
	RolUsuario rolUsuario = new RolUsuario();
	model.addAttribute("rolUsuario", rolUsuario);
	return "registracionRolUsuario";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute(value = "rolUsuario") RolUsuario rolUsuario, BindingResult result) {
	validator.validate(rolUsuario, result);
	ModelAndView mv = new ModelAndView("registracionRolUsuario");
	if (!result.hasErrors()) {
	    rolUsuarioDao.save(rolUsuario);
	    rolUsuario = new RolUsuario();
	    mv.addObject("rolUsuario", rolUsuario);
	}
	mv.addObject("listaRolUsuarios", rolUsuarioDao.findAll());
	return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute(value = "rolUsuario") RolUsuario rolUsuario, BindingResult result) {
	validator.validate(rolUsuario, result);
	ModelAndView mv = new ModelAndView("registracionRolUsuario");
	if (!result.hasErrors()) {
	    rolUsuarioDao.update(rolUsuario);
	    rolUsuario = new RolUsuario();
	    mv.addObject("rolUsuario", rolUsuario);
	}
	mv.addObject("listaRolUsuarios", rolUsuarioDao.findAll());
	return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@ModelAttribute(value = "rolUsuario") RolUsuario rolUsuario, BindingResult result) {
	validator.validate(rolUsuario, result);
	ModelAndView mv = new ModelAndView("registracionRolUsuario");
	if (!result.hasErrors()) {
	    rolUsuarioDao.delete(rolUsuario);
	    rolUsuario = new RolUsuario();
	    mv.addObject("rolUsuario", rolUsuario);
	}
	mv.addObject("listaRolUsuarios", rolUsuarioDao.findAll());
	return mv;
    }

}