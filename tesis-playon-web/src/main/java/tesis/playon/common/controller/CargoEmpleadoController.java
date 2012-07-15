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
import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 *
 */
@Controller
@RequestMapping(value = "/cargos-empleado")
public class CargoEmpleadoController {
    
    protected static Logger logger = Logger.getLogger("CargoEmpleadoController");

    @Autowired
    private CargoEmpleadoDao cargoEmpleadoDao;

    @RequestMapping(method = RequestMethod.GET)
    public String getCargosEmpleado(Model model) {
	logger.debug("Recibida peticion para mostrar todos los cargos de empleados");
	List<CargoEmpleado> cargosEmpleado = cargoEmpleadoDao.findAll();
	model.addAttribute("cargosEmpleado", cargosEmpleado);
	return "cargoempleadolist";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
	logger.debug("Recibida peticion para mostrar pagina agregar");
	model.addAttribute("cargoEmpleadoAtributo", new CargoEmpleado());
	return "cargoempleadoadd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("cargoEmpleadoAtributo") CargoEmpleado cargoEmpleado, Model model) {
	logger.debug("Recibido pedido para agregar un cargo de empleado");
	cargoEmpleadoDao.save(cargoEmpleado);
	model.addAttribute("mensaje", "Ha agregado un nuevo cargo de empleado de playas de estacionamiento al sistema");
	this.getCargosEmpleado(model);
	return "cargoempleadolist";
      

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getUpdate(@RequestParam(value = "nombre") String nombre, Model model) {
	logger.debug("Recibida la peticion para mostrar la pagina de edicion");
	model.addAttribute("cargoEmpleadoAtributo", cargoEmpleadoDao.findByNombreCargo(nombre));
	return "cargoempleadoupdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute("cargoEmpleadoAtributo") CargoEmpleado cargoEmpleado, Model model) {
	logger.debug("Recibida la peticion para actualiza un cargo de empleado");
	cargoEmpleadoDao.update(cargoEmpleado);
	model.addAttribute("id", cargoEmpleado.getId());
	return "cargoempleadoupdated";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id, Model model) {
	logger.debug("Recibida la peticion para borra un cargo de empleado existente");
	CargoEmpleado cargoEmpleado = new CargoEmpleado();
	cargoEmpleado.setId(id);
	cargoEmpleadoDao.delete(cargoEmpleado);
	model.addAttribute("id", id);
	return "cargoempleadodeleted";
    }
}
