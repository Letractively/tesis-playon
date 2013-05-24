package tesis.playon.restful.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.CuentaCliente;
import tesis.playon.restful.service.ICuentaClienteService;

@Controller("cuentaClienteController")
public class CuentaClienteController {

    @Resource(name = "cuentaClienteService")
    private ICuentaClienteService cuentaClienteService;

    @RequestMapping(value = "/cuentasclientes", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<CuentaCliente> getCliente() {
	List<CuentaCliente> result = new ArrayList<CuentaCliente>();
	result = cuentaClienteService.findAll();
	return result;
    }

    @RequestMapping(value = "/cuentacliente/{nroCliente}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    CuentaCliente getCuentaCliente(@PathVariable("nroCliente") Integer nroCliente) {
	Cliente cliente = new Cliente();
	cliente.setId(nroCliente);
	return cuentaClienteService.findByNroCliente(cliente);
    }
}