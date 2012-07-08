/**
 * 
 */
package Testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.business_object.dao.ICargoEmpleadoDao;
import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 *
 */
public class TestCargoEmpleado {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	ICargoEmpleadoDao icargo = (ICargoEmpleadoDao)appContext.getBean("cargoEmpleadoDao");
	
	System.out.println("\n");
	/** insert **/
	CargoEmpleado cargo = new CargoEmpleado("Operario", "Operario de Playa");
	CargoEmpleado cargo1 = new CargoEmpleado("Gerente", "Gerente de Playa");
	icargo.save(cargo);
	icargo.save(cargo1);
	List<CargoEmpleado> cargos = icargo.findAll();
	System.out.println("Nuevos Cargos de empleados:");
	for (CargoEmpleado cargoEmpleado : cargos) {
	    System.out.println(cargoEmpleado);
	}
	
	System.out.println("\n");	
	/** select **/
	CargoEmpleado cargoSolicitada = icargo.findByNombreCargo("Operario");
	System.out.println("Cargo buscado: \t" + cargoSolicitada);
	
	System.out.println("\n");
	/** update **/
	cargo.setNombre("Propietario");
	icargo.update(cargo);
	System.out.println("Cargo Modificada:\t" + icargo.findByNombreCargo("Propietario"));

	System.out.println("\n");
	/** delete **/
	icargo.delete(cargo);
	icargo.delete(cargo1);
	cargos = icargo.findAll();
	System.out.println("Marcas de vehiculos restantes:");
	for (CargoEmpleado cargoEmpleado : cargos) {
	    System.out.println(cargoEmpleado);
	}

	System.out.println("\nListo!");
    }
}
