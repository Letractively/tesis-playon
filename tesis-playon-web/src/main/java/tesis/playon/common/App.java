package tesis.playon.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.business_object.RolUsuarioBo;
import tesis.playon.web.model.RolUsuario;

public class App {
    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	RolUsuarioBo rolUsuarioBo = (RolUsuarioBo) appContext.getBean("rolUsuarioBo");

	/** insert **/
	RolUsuario rolUsuario = new RolUsuario();
	rolUsuario.setNombre("cliente");
	rolUsuarioBo.save(rolUsuario);

	/** select **/
	RolUsuario otroRolUsuario = rolUsuarioBo.findByNombreRolUsuario("Cliente");
	System.out.println(otroRolUsuario);

	/** update **/
	otroRolUsuario.setNombre("Administrador");
	rolUsuarioBo.update(otroRolUsuario);

	/** delete **/
	rolUsuarioBo.delete(otroRolUsuario);

	System.out.println("Done");
    }
}