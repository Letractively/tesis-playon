package tesis.playon.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tesis.playon.restful.service.ITipoDocService;

@Controller("tipoDocController")
public class TipoDocController {

    @Autowired
    private ITipoDocService tipoDocService;

    
}