/**
 * 
 */
package Testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.business_object.dao.IRolUsuarioDao;
import tesis.playon.web.business_object.dao.IUsuarioSistemaDao;
import tesis.playon.web.model.RolUsuario;
import tesis.playon.web.model.UsuarioSistema;

/**
 * @author Pablo
 *
 */
public class TestUsuarioSistema {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	
	IRolUsuarioDao rolUsuarioDao = (IRolUsuarioDao) appContext.getBean("rolUsuarioDao");
	/** insert **/
	RolUsuario rolUsuario = new RolUsuario("Socio", "Usuario con el perfil de cliente.");
	rolUsuarioDao.save(rolUsuario);
	rolUsuario = rolUsuarioDao.findByNombreRolUsuario("Socio");
	System.out.println(rolUsuario);
	RolUsuario rolUsuario1 = new RolUsuario("Anonimo", "Usuario no registrado en el sistema.");
	rolUsuarioDao.save(rolUsuario1);
	rolUsuario1 = rolUsuarioDao.findByNombreRolUsuario("Anonimo");
	RolUsuario rolUsuario2 = new RolUsuario("Invitado", null);
	rolUsuarioDao.save(rolUsuario2);
	rolUsuario2 = rolUsuarioDao.findByNombreRolUsuario("Invitado");
	
	
//	IUsuarioSistemaDao usuarioSistemaDao = (IUsuarioSistemaDao) appContext.getBean("usuarioSistemaDao");
//
//	/** insert **/
//	UsuarioSistema usuarioSistema = new UsuarioSistema(rolUsuario);
//	UsuarioSistema usuarioSistema1 = new UsuarioSistema(rolUsuario1);
//	usuarioSistemaDao.save(usuarioSistema);
//	usuarioSistemaDao.save(usuarioSistema1);
//	List<UsuarioSistema> usuarios = usuarioSistemaDao.findAll();
//	System.out.println("\nUsuarios del Sistema:");
//	for (UsuarioSistema _usuario : usuarios) {
//	    System.out.println(_usuario);
//	}
//
//	/** select **/
//	UsuarioSistema otroUsuario = usuarioSistemaDao.findByNombreUsuarioSistema("Anonimo");
//	System.out.println("\nEncontrado:\t" + otroUsuario);
//	
//	/** update **/
//	usuarioSistema.setRolUsuario(rolUsuario2);
//	usuarioSistemaDao.update(usuarioSistema);
//	System.out.println("\nModificado:\t" + usuarioSistemaDao.findByNombreUsuarioSistema("Invitado"));
//	
//	System.out.println("\n");
//	/** delete **/
//	usuarioSistemaDao.delete(usuarioSistema);
//	usuarioSistemaDao.delete(usuarioSistema1);
//	usuarios = usuarioSistemaDao.findAll();
//	System.out.println("\nRestantes:");
//	for (UsuarioSistema _usuario : usuarios) {
//	    System.out.println(_usuario);
//	}
	rolUsuarioDao.delete(rolUsuario);
	rolUsuarioDao.delete(rolUsuario1);
	rolUsuarioDao.delete(rolUsuario2);
	

	System.out.println("\nListo!");
    }
}
