/**
 * 
 */
package tesis.playon.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import tesis.playon.web.model.PerfilPlaya;

/**
 * @author pablo
 * 
 */
public class ConvertImageToArrayBytes implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    public static byte[] getArrayByteFotoPerfil(UploadedFile foto, PerfilPlaya perfil) {
	// Just to demonstrate what information you can get from the uploaded file.
	System.out.println("File type: " + foto.getContentType());
	System.out.println("File name: " + foto.getFileName());
	System.out.println("File size: " + foto.getSize() + " bytes");

	// Prepare filename prefix and suffix for an unique filename in upload folder.
	String prefix = FilenameUtils.getBaseName(foto.getFileName());
	String suffix = FilenameUtils.getExtension(foto.getFileName());

	File file = null;
	OutputStream output = null;
	FileInputStream fis = null;
	try {

	    // Create file with unique name in upload folder and write to it.
	    file = File.createTempFile(prefix + "_", "." + suffix,
		    new File(File.separator + "tmp"));

	    System.out.println(file.getAbsolutePath());
	    output = new FileOutputStream(file);
	    IOUtils.copy(foto.getInputstream(), output);

	    byte[] bFile = new byte[(int) file.length()];

	    fis = new FileInputStream(file);
	    fis.read(bFile);
	    fis.close();

	    // Show succes message.
	    FacesContext.getCurrentInstance()
		    .addMessage(
			    "uploadForm",
			    new FacesMessage(FacesMessage.SEVERITY_INFO,
				    "La foto de perfil se actualizó correctamente!", null));
	    return bFile;
	} catch (IOException e) {
	    // Cleanup.
	    if (file != null)
		file.delete();
	    // Show error message.
	    e.printStackTrace();
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo grabar la foto",
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} finally {
	    IOUtils.closeQuietly(output);
	}
	return null;
    }

    public static void getFotoPerfil(PerfilPlaya perfil) {
	try {
	    if (perfil.getFotoPerfil() != null) {
		File file = new File(File.separator + "tmp" + File.separator + perfil.getNombreFoto());

		    // FileOutputStream fos = new FileOutputStream("images\\output.jpg"); //windows
		    FileOutputStream fos = new FileOutputStream(file);
		    
		    fos.write(perfil.getFotoPerfil());
		    fos.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}