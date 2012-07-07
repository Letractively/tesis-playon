package tesis.playon.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.business_object.IRolUsuarioBo;
import tesis.playon.web.business_object.dao.IPaisDao;
import tesis.playon.web.business_object.dao.IProvinciaDao;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.model.RolUsuario;

public class App {
    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	// Test para Rol Usuario
	IRolUsuarioBo rolUsuarioBo = (IRolUsuarioBo) appContext.getBean("rolUsuarioBo");

	/** insert **/
	RolUsuario rolUsuario = new RolUsuario();
	rolUsuario.setNombre("Cliente");
	rolUsuario.setDescripcion("Usuario con el perfil de cliente.");
	rolUsuarioBo.save(rolUsuario);

	/** select **/
	RolUsuario otroRolUsuario = rolUsuarioBo.findByNombreRolUsuario("Cliente");
	System.out.println(otroRolUsuario);

	/** update **/
	otroRolUsuario.setNombre("Administrador");
	rolUsuario.setDescripcion("Usuario con el perfil de administrador.");
	rolUsuarioBo.update(otroRolUsuario);

	/** delete **/
	rolUsuarioBo.delete(otroRolUsuario);

	// Test para Pais
	IPaisDao paisDao = (IPaisDao) appContext.getBean("paisDao");

	/** insert **/
	Pais pais = new Pais("Argentina");
	pais.setNombre("Argentina");
	paisDao.save(pais);

	/** select **/
	Pais otroPais = paisDao.findByNombrePais("Argentina");
	System.out.println(otroPais);

	/** update **/
	otroPais.setNombre("Republica Argentina");
	paisDao.update(otroPais);

	// Test para Provincia
	IProvinciaDao provinciaDao = (IProvinciaDao) appContext.getBean("provinciaDao");

	/** insert **/
	Provincia provincia = new Provincia("Cordoba", otroPais);
	provinciaDao.save(provincia);

	/** select **/
	Provincia otroProvincia = provinciaDao.findByNombreProvincia("Cordoba");
	System.out.println(otroProvincia.toString());

	/** update **/
	otroProvincia.setNombre("Buenos Aires");
	provinciaDao.update(otroProvincia);

	/** select **/
	otroPais = paisDao.findByNombrePais("Republica Argentina");
	System.out.println(otroPais);

//	/** delete **/
//	provinciaDao.delete(otroProvincia);
//
//	/** delete **/
//	paisDao.delete(otroPais);

	System.out.println("Done");
    }
}