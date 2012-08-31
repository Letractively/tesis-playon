/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Usuario;
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
    
    private PerfilPlaya perfil;
    
    private Integer calificacion;
    
    private List<String> fotosList;
    
    private UploadedFile fotoPerfilFile;
    
    private String telefono;
    
    private String email;
    
    private Integer disponibilidad;
    
    private String fotoPerfil;
    
    public String updatePerfil(){
	try{
	    if(fotoPerfilFile != null) {
		
	    }
	    perfil.getPlaya().setDisponibilidad(getDisponibilidad());
	    perfil.getPlaya().setEmail(getEmail());
	    perfil.getPlaya().setTelefono(getTelefono());
	    
	    getPlayaService().update(perfil.getPlaya());
	    getPerfilPlayaService().update(perfil);
	    return "/playa/perfilplaya";
	}catch(Exception ex){
	    
	}
	return "/playa/gerencia/perfilplayaedit";
    }

    public PerfilPlaya getPerfil() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	perfil = getPerfilPlayaService().findByPlaya(user.getPlaya());
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

    public Integer getCalificacion() {
	calificacion= Math.round(perfil.getTotalCalificaciones() / perfil.getCantidadVotantes());
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<String> getFotosList() {
	fotosList= new ArrayList<String>();
	fotosList.add("sinfoto.jpg");
	fotosList.add("logo_playon_admin.png");
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

    //datos para mostrar en el mapa
    
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



    LatitudlongitudUtil latLonUtil;
    
    private GeoposicionDePlaya respuesta;
    
    private String coordenadas;
    
    private final MapModel advancedModel = new DefaultMapModel();
    
    private Marker marker;

    public String getCoordenadas() {
	try{
	latLonUtil = new LatitudlongitudUtil();
	// GeoposicionDePlaya
	respuesta = latLonUtil.getLocationFromAddress(perfil.getPlaya().getDomicilio() + ", Cordoba, CBA, Argentina");
	coordenadas = respuesta.toString();
	LatLng coord1 = new LatLng(perfil.getPlaya().getLatitud(), perfil.getPlaya().getLongitud());
	advancedModel.addOverlay(new Marker(coord1, perfil.getPlaya().toString2(), null,
		"http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));
        return coordenadas;
        
	}catch(Exception e){
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