/**
 * 
 */
package tesis.playon.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.Foto;
import tesis.playon.web.model.FotoPublicidad;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Usuario;

/**
 * @author pablo
 * 
 */
public class WriteImage implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    public static void getFotoPerfil(PerfilPlaya perfil) {
	ExternalContext extContext = null;
	try {
	    if (perfil.getFotoPerfil() != null) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "fotos_perfil_playas") + sep;
		System.out.println(extContext.getRealPath("resources" + sep + "fotos_perfil_playas"));

		File file = new File(path + perfil.getNombreFoto());

		// FileOutputStream fos = new FileOutputStream("images\\output.jpg"); //windows
		FileOutputStream fos = new FileOutputStream(file);

		fos.write(perfil.getFotoPerfil());
		fos.close();

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void writeFotos(List<Foto> fotosList) {
	ExternalContext extContext = null;
	try {
	    for (Foto foto : fotosList) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "fotos_playas") + sep;

		File file = new File(path + foto.getNombre());

		// FileOutputStream fos = new FileOutputStream("images\\output.jpg"); //windows
		FileOutputStream fos = new FileOutputStream(file);

		fos.write(foto.getImage());
		fos.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public static void writeFotosPublicidad(List<FotoPublicidad> fotosList) {
	ExternalContext extContext = null;
	try {
	    for (FotoPublicidad foto : fotosList) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "fotos_publicidad") + sep;

		File file = new File(path + foto.getNombre());

		// FileOutputStream fos = new FileOutputStream("images\\output.jpg"); //windows
		FileOutputStream fos = new FileOutputStream(file);

		fos.write(foto.getImage());
		fos.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void getFotoPerfilCliente(Usuario usuario) {
	ExternalContext extContext = null;
	try {
	    if (usuario.getFotoPerfil() != null) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "fotos_perfil_clientes") + sep;
		System.out.println(extContext.getRealPath("resources" + sep + "fotos_perfil_playas"));

		File file = new File(path + usuario.getNombreUser() + ".jpg");

		// FileOutputStream fos = new FileOutputStream("images\\output.jpg"); //windows
		FileOutputStream fos = new FileOutputStream(file);

		fos.write(usuario.getFotoPerfil());
		fos.close();

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}