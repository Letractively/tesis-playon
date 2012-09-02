/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IFotoService;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.LatitudlongitudUtil;
import tesis.playon.web.util.LatitudlongitudUtil.GeoposicionDePlaya;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "perfilPlayaMB")
@RequestScoped
public class PerfilPlayaManagedBean {

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;

    @ManagedProperty(value = "#{FotoService}")
    IFotoService fotoService;

    private PerfilPlaya perfil;

    private static String nombreComercial;

    private Integer calificacion;

    private List<String> fotosList;

    private UploadedFile fotoPerfilFile;

    private String telefono;

    private String email;

    private Integer disponibilidad;

    private String fotoPerfil;

    private Playa playaSelected;

    @PostConstruct
    public void init() {
	this.telefono = getPerfil().getPlaya().getTelefono();
	this.email = getPerfil().getPlaya().getEmail();
	this.disponibilidad = getPerfil().getPlaya().getDisponibilidad();
    }

    public String updatePerfil() {
	Playa playa = new Playa();
	try {
	    playa = getPerfil().getPlaya();
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmail());
	    playa.setDisponibilidad(getDisponibilidad());
	    getPerfilPlayaService().update(perfil);
	    getPlayaService().update(playa);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se modificó correctamente el perfil de la playa", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    
	    return "/playa/perfilplaya";
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "No se pudo modificar el perfil de la playa", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return "error";
    }

    public void upload() {
	// Just to demonstrate what information you can get from the uploaded file.
	System.out.println("File type: " + fotoPerfilFile.getContentType());
	System.out.println("File name: " + fotoPerfilFile.getFileName());
	System.out.println("File size: " + fotoPerfilFile.getSize() + " bytes");

	// Prepare filename prefix and suffix for an unique filename in upload folder.
	String prefix = FilenameUtils.getBaseName(fotoPerfilFile.getFileName());
	String suffix = FilenameUtils.getExtension(fotoPerfilFile.getFileName());

	String pathFile = "/home/pablo/workspace/tesis-playon-web/" + "src" + File.separator + "main"
		+ File.separator + "webapp" + File.separator + "resources" + File.separator + "fotos" + File.separator
		+ "playas" + File.separator + "fotoPerfil";

	File file = null;
	OutputStream output = null;
	try {

	    // Create file with unique name in upload folder and write to it.
	    file = File.createTempFile(prefix + "_", "." + suffix, new File(File.separator + "tmp" + File.separator
		    + "fileUpload"));
	    System.out.println(file.getAbsolutePath());
	    output = new FileOutputStream(file);
	    IOUtils.copy(fotoPerfilFile.getInputstream(), output);

	    File dest = new File(File.separator + "tmp" + File.separator + "fileUpload" + File.separator
		    + "fotoPerfil_" + getPerfil().getPlaya().getId() + "." + suffix);

	    // Cambio el nombre del archivo a fotoPerfil_PlayaID
	    boolean cambioNombre = file.renameTo(dest);

	    if (cambioNombre) {

		dest = new File(File.separator + "tmp" + File.separator + "fileUpload" + File.separator + "fotoPerfil_"
			+ getPerfil().getPlaya().getId() + "." + suffix);

		File copia = new File(pathFile + File.separator + "fotoPerfil_"
			+ getPerfil().getPlaya().getId() + "." + suffix);

		InputStream inStream = new FileInputStream(dest);
		OutputStream outStream = new FileOutputStream(copia);

		byte[] buffer = new byte[6144];

		int length;
		// copy the file content in bytes
		while ((length = inStream.read(buffer)) > 0) {

		    outStream.write(buffer, 0, length);

		}

		inStream.close();
		outStream.close();
	    }

	    fotoPerfil = "fotoPerfil_" + getPerfil().getPlaya().getId() + "." + suffix;

	    PerfilPlaya perfilPlaya = new PerfilPlaya();
	    perfilPlaya = getPerfil();

	    perfilPlaya.setFotoPerfil(fotoPerfil);
	    getPerfilPlayaService().update(perfilPlaya);
	    
	    // Show succes message.
	    FacesContext.getCurrentInstance().addMessage("uploadForm",
		    new FacesMessage(FacesMessage.SEVERITY_INFO, "File upload succeed!", null));
	} catch (IOException e) {
	    // Cleanup.
	    if (file != null)
		file.delete();
	    // Show error message.
	    // Always log stacktraces (with a real logger).
	    e.printStackTrace();
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo grabar la foto",
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} finally {
	    IOUtils.closeQuietly(output);
	}
    }

    public PerfilPlaya getPerfil() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	perfil = getPerfilPlayaService().findByPlaya(user.getPlaya());
	nombreComercial = perfil.getPlaya().getNombreComercial();
	return perfil;
    }

    public void setPerfil(PerfilPlaya perfil) {
	this.perfil = perfil;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
	return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
	this.perfilPlayaService = perfilPlayaService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IFotoService getFotoService() {
	return fotoService;
    }

    public void setFotoService(IFotoService fotoService) {
	this.fotoService = fotoService;
    }

    public Integer getCalificacion() {
	calificacion = Math.round(perfil.getTotalCalificaciones() / perfil.getCantidadVotantes());
	return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
	this.calificacion = calificacion;
    }

    public List<String> getFotosList() {
	fotosList = new ArrayList<String>();
	return fotosList;
    }

    public void setFotosList(List<String> fotosList) {
	this.fotosList = fotosList;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public Playa getPlayaSelected() {
	playaSelected = getPlayaService().findByNombreComercial(nombreComercial);
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	this.playaSelected = playaSelected;
    }

    public UploadedFile getFotoPerfilFile() {
	return fotoPerfilFile;
    }

    public void setFotoPerfilFile(UploadedFile fotoPerfilFile) {
	this.fotoPerfilFile = fotoPerfilFile;
    }

    public String getFotoPerfil() {
	return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
	this.fotoPerfil = fotoPerfil;
    }

    // datos para mostrar en el mapa
    LatitudlongitudUtil latLonUtil;

    private GeoposicionDePlaya respuesta;

    private String coordenadas;

    private final MapModel advancedModel = new DefaultMapModel();

    private Marker marker;

    public String getCoordenadas() {
	try {
	    latLonUtil = new LatitudlongitudUtil();
	    // GeoposicionDePlaya
	    respuesta = latLonUtil.getLocationFromAddress(perfil.getPlaya().getDomicilio()
		    + ", Cordoba, CBA, Argentina");
	    coordenadas = respuesta.toString();
	    LatLng coord1 = new LatLng(perfil.getPlaya().getLatitud(), perfil.getPlaya().getLongitud());
	    advancedModel.addOverlay(new Marker(coord1, perfil.getPlaya().toString2(), null,
		    "http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));
	    return coordenadas;

	} catch (Exception e) {
	    e.getStackTrace();
	}
	return "";
    }

    public void setCoordenadas(String coordenadas) {
	this.coordenadas = coordenadas;
    }

    public GeoposicionDePlaya getRespuesta() {
	return respuesta;
    }

    public void setRespuesta(GeoposicionDePlaya respuesta) {
	this.respuesta = respuesta;
    }

    public LatitudlongitudUtil getLatLonUtil() {
	return latLonUtil;
    }

    public void setLatLonUtil(LatitudlongitudUtil latLonUtil) {
	this.latLonUtil = latLonUtil;
    }

    public MapModel getAdvancedModel() {
	return advancedModel;
    }

    public Marker getMarker() {
	return marker;
    }

    public void setMarker(Marker marker) {
	this.marker = marker;
    }
}