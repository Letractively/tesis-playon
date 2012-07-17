/**
 * 
 */
package tesis.playon.common.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tesis.playon.web.dao.impl.CargoEmpleadoDao;
import tesis.playon.web.dao.impl.EmpleadoDao;
import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Empleado;

/**
 * @author Pablo
 * 
 */
@Controller
@RequestMapping(value = "/empleado")
public class EmpleadoController {

    protected static Logger logger = Logger.getLogger("EmpleadoController");

    @Autowired
    private EmpleadoDao empleadoDao;

    @Autowired
    private CargoEmpleadoDao cargoEmpleadoDao;

    @ModelAttribute("listaEmpleados")
    public List<Empleado> popularEmpleados() {
	return empleadoDao.findAll();
    }
    
    @ModelAttribute("listaCargosEmpleado")
    public List<CargoEmpleado> popularCargosEmpleado() {
	return cargoEmpleadoDao.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getEmpleados(Model model) {
	logger.debug("Recibida peticion para mostrar todos los empleados de una playa de estacionamiento");
	List<Empleado> empleados = empleadoDao.findAll();
	model.addAttribute("empleados", empleados);
	return "empleadolist";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
	logger.debug("Recibida peticion para mostrar pagina agregar");
	model.addAttribute("empleadoAtributo", new Empleado());
	List<CargoEmpleado> cargosEmpleado = cargoEmpleadoDao.findAll();
	model.addAttribute("cargosEmpleado", cargosEmpleado);
	model.addAttribute("listaCargosEmpleado", cargoEmpleadoDao.findAll());
	return "empleadoadd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("empleadoAtributo") Empleado empleado) {
	logger.debug("Recibido pedido para agregar un empleado de una playa de estacionamiento");
	empleadoDao.save(empleado);
	return "empleadoadded";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getUpdate(@RequestParam(value = "legajo") Integer legajo, Model model) {
	logger.debug("Recibida la peticion para mostrar la pagina de edicion");
	model.addAttribute("empleadoAtributo", empleadoDao.findByLegajo(legajo));
	return "empleadoupdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute("empleadoAtributo") Empleado empleado, Model model) {
	logger.debug("Recibida la peticion para actualiza un empleado");
	empleadoDao.update(empleado);
	model.addAttribute("id", empleado.getId());
	model.addAttribute("listaCargosEmpleado", cargoEmpleadoDao.findAll());
	return "empleadoupdated";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id, Model model) {
	logger.debug("Recibida la peticion para borra un empleado existente");
	Empleado empleado = empleadoDao.findById(id);
	empleadoDao.delete(empleado);
	model.addAttribute("id", id);
	return "empleadodeleted";
    }
}