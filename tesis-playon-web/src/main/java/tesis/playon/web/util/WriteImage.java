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

    public static void getFotoPerfilPlaya(PerfilPlaya perfil) {
	ExternalContext extContext = null;
	try {
	    if (perfil.getFotoPerfil() != null) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "fotos_perfil_playas") + sep;
		File file = new File(path + perfil.getId() + "_" + perfil.getNombreFoto());

		if (!file.exists()) {
		    FileOutputStream fos = new FileOutputStream(file);
		    fos.write(perfil.getFotoPerfil());
		    fos.close();
		}
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

		File file = new File(path + foto.getId() + "_" + foto.getNombre());

		// FileOutputStream fos = new FileOutputStream("images\\output.jpg"); //windows
		FileOutputStream fos = new FileOutputStream(file);

		fos.write(foto.getImage());
		fos.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void writeFotoTemporal(FotoPublicidad fotoPublicidad) {
	ExternalContext extContext = null;
	try {
	    if (fotoPublicidad != null) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "tmp") + sep;
		File file = new File(path + fotoPublicidad.getNombre());

		FileOutputStream fos = new FileOutputStream(file);
		fos.write(fotoPublicidad.getImage());
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
		File file = new File(path + foto.getId() + "_" + foto.getNombre());

		if (!file.exists()) {
		    FileOutputStream fos = new FileOutputStream(file);

		    fos.write(foto.getImage());
		    fos.close();
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void getFotoPerfilUsuario(Usuario usuario) {
	ExternalContext extContext = null;
	try {
	    if (usuario.getFotoUsuario() != null) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "fotos_perfil_usuarios") + sep;
		File file = new File(path + usuario.getNombreUser() + ".jpg");

		if (!file.exists()) {
		    FileOutputStream fos = new FileOutputStream(file);

		    fos.write(usuario.getFotoUsuario().getFotoUsuario());
		    fos.close();
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public static void borrarFotoPerfilUsuarioAntigua(Usuario usuario) {
	ExternalContext extContext = null;
	try {
	    if (usuario.getFotoUsuario() != null) {
		String sep = File.separator;
		extContext = FacesContext.getCurrentInstance().getExternalContext();

		String path = extContext.getRealPath("resources" + sep + "fotos_perfil_usuarios") + sep;
		File file = new File(path + usuario.getNombreUser() + ".jpg");
		
		if (file.exists()) {
		    file.delete();
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}