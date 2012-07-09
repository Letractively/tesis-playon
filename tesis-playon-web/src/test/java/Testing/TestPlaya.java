/**
 * 
 */
package Testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.business_object.dao.ICargoEmpleadoDao;
import tesis.playon.web.business_object.dao.IEmpleadoDao;
import tesis.playon.web.business_object.dao.IPlayaDao;
import tesis.playon.web.business_object.dao.IUsuarioDao;
import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;

/**
 * @author Pablo
 *
 */
public class TestPlaya {

    public static void main(String[] args) {
	
//	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
//	
//	IUsuarioDao usuarioDao = (IUsuarioDao) appContext.getBean("usuarioDao");
//	ICargoEmpleadoDao cargoEmpladoDao = (ICargoEmpleadoDao)appContext.getBean("cargoEmpleadoDao");
//	
//	Usuario usuario = usuarioDao.findByNombreUsuario("pablo_la_31");
//	Usuario usuario1 = usuarioDao.findByNombreUsuario("pablo_la31");
//	CargoEmpleado cargoEmpleado = cargoEmpladoDao.findByNombreCargo("Encargado");
//	CargoEmpleado cargoEmpleado1 = cargoEmpladoDao.findByNombreCargo("Playero");
//	
//
//	/** TEST PLAYA DE ESTACIONAMIENTO **/
//	IPlayaDao playaDao = (IPlayaDao) appContext.getBean("playaDao");
//
//	/** insert **/
//	Empleado playa = new Playa("Ituzaingo Parking", estado)
//	Empleado playa1 = new Empleado(1003, cargoEmpleado1, usuario1);
//	playaDao.save(playa);
//	playaDao.save(playa1);
//	List<Empleado> empleados = playaDao.findAll();
//	System.out.println("\nEmpleados:");
//	for (Empleado _empleado : empleados) {
//	    System.out.println(_empleado);
//	}
//
//	/** select **/
//	Empleado otroEmpleado = playaDao.findByLegajo(1002);
//	System.out.println("\n");
//	System.out.println("\nEncontrado:\t" + otroEmpleado);
//	
//	/** update **/
//	playa.setLegajo(2000);
//	playaDao.update(playa);
//	System.out.println("\n");
//	System.out.println("\nModificado:\t" + playaDao.findByLegajo(2000));
//	
//	System.out.println("\n");
//	/** delete **/
//	playaDao.delete(playa);
//	playaDao.delete(playa1);
//	empleados = playaDao.findAll();
//	System.out.println("\n");
//	System.out.println("\nRestantes:");
//	for (Empleado _empleado : empleados) {
//	    System.out.println(_empleado);
//	}
//
//	System.out.println("\nListo!");
    }
}
