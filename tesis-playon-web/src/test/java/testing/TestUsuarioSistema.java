package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IRolUsuarioDao;
import tesis.playon.web.dao.IUsuarioDao;
import tesis.playon.web.dao.IUsuarioSistemaDao;
import tesis.playon.web.model.RolUsuario;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.UsuarioSistema;

/**
 * @author Pablo
 *
 */
public class TestUsuarioSistema {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	
	IUsuarioDao usuarioDao = (IUsuarioDao) appContext.getBean("usuarioDao");
	IRolUsuarioDao rolUsuarioDao = (IRolUsuarioDao) appContext.getBean("rolUsuarioDao");
	/** insert **/
	RolUsuario rolUsuario = rolUsuarioDao.findByNombreRolUsuario("Administrador");
	RolUsuario rolUsuario1 = rolUsuarioDao.findByNombreRolUsuario("Auditor");
	RolUsuario rolUsuario2 = rolUsuarioDao.findByNombreRolUsuario("usuario");
	Usuario usuario = usuarioDao.findByNombreUsuario("pablo_la_31");
	Usuario usuario1 = usuarioDao.findByNombreUsuario("pablo_la31");
	
	
	IUsuarioSistemaDao usuarioSistemaDao = (IUsuarioSistemaDao) appContext.getBean("usuarioSistemaDao");

	/** insert **/
	UsuarioSistema usuarioSistema = new UsuarioSistema(rolUsuario, usuario);
	UsuarioSistema usuarioSistema1 = new UsuarioSistema(rolUsuario1, usuario1);
	usuarioSistemaDao.save(usuarioSistema);
	usuarioSistemaDao.save(usuarioSistema1);
	List<UsuarioSistema> usuarios = usuarioSistemaDao.findAll();
	System.out.println("\nUsuarios del Sistema:");
	for (UsuarioSistema _usuario : usuarios) {
	    System.out.println(_usuario);
	}

	/** select **/
//	UsuarioSistema otroUsuario = usuarioSistemaDao.findByNombreUsuarioSistema(usuario);
//	System.out.println("\nEncontrado:\t" + otroUsuario);
	
	/** update **/
	usuarioSistema.setRolUsuario(rolUsuario2);
	usuarioSistemaDao.update(usuarioSistema);
//	System.out.println("\nModificado:\t" + usuarioSistemaDao.findByNombreUsuarioSistema(usuario));
	
	System.out.println("\n");
	/** delete **/
	usuarioSistemaDao.delete(usuarioSistema);
	usuarioSistemaDao.delete(usuarioSistema1);
	usuarios = usuarioSistemaDao.findAll();
	System.out.println("\nRestantes:");
	for (UsuarioSistema _usuario : usuarios) {
	    System.out.println(_usuario);
	}
	

	System.out.println("\nListo!");
    }
}
