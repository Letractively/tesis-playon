package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IRolUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.LatitudlongitudUtil;
import tesis.playon.web.util.LatitudlongitudUtil.GeoposicionDePlaya;
import tesis.playon.web.util.WriteImage;

@ManagedBean(name = "busquedaplayaMB")
@RequestScoped
public class BusquedaPlayasManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PLAYAS = "playalist";

    private static final String ERROR = "error";

    private MapModel simpleModel;
    private final MapModel advancedModel = new DefaultMapModel();
    private Marker marker;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    @ManagedProperty(value = "#{RolUsuarioService}")
    IRolUsuarioService rolUsuarioService;

    List<Playa> playaList;

    private static List<Playa> playaResultadoBusqueda;

    LatitudlongitudUtil latLonUtil;

    // Atributos de las playas.
    private String cuit;

    private Integer disponibilidad;

    private String direccionBusqueda;

    private String domicilio;

    private String nombreComercial;

    private String razonSocial;

    private Barrio barrio;

    private String telefono;

    private String emailPlaya;

    private EstadoPlaya estado;

    private Estadia estadia;

    public IEstadoPlayaService getEstadoPlayaService() {
	return estadoPlayaService;
    }

    public void setEstadoPlayaService(IEstadoPlayaService estadoPlayaService) {
	this.estadoPlayaService = estadoPlayaService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IBarrioService getBarrioService() {
	return barrioService;
    }

    public void setBarrioService(IBarrioService barrioService) {
	this.barrioService = barrioService;
    }

    public IRolUsuarioService getRolUsuarioService() {
	return rolUsuarioService;
    }

    public void setRolUsuarioService(IRolUsuarioService rolUsuarioService) {
	this.rolUsuarioService = rolUsuarioService;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
	return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
	this.perfilPlayaService = perfilPlayaService;
    }

    public LatitudlongitudUtil getLatLonUtil() {
	return latLonUtil;
    }

    public void setLatLonUtil(LatitudlongitudUtil latLonUtil) {
	this.latLonUtil = latLonUtil;
    }

    public String getCuit() {
	return cuit;
    }

    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getNombreComercial() {
	return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
	this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmailPlaya() {
	return emailPlaya;
    }

    public void setEmailPlaya(String emailPlaya) {
	this.emailPlaya = emailPlaya;
    }

    public EstadoPlaya getEstado() {
	return estado;
    }

    public void setEstado(EstadoPlaya estado) {
	this.estado = estado;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public GeoposicionDePlaya getRespuesta() {
	return respuesta;
    }

    public void setRespuesta(GeoposicionDePlaya respuesta) {
	this.respuesta = respuesta;
    }

    public String getCoordenadas() {
	return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
	this.coordenadas = coordenadas;
    }

    public static Playa getPlayaSelected() {
	return playaSelected;
    }

    public static void setPlayaSelected(Playa playaSelected) {
	BusquedaPlayasManagedBean.playaSelected = playaSelected;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public static String getListaPlayas() {
	return LISTA_PLAYAS;
    }

    public static String getError() {
	return ERROR;
    }

    public void setMarker(Marker marker) {
	this.marker = marker;
    }

    private Playa playa;

    private Integer distancia = 25;

    private GeoposicionDePlaya respuesta;

    private String coordenadas;

    // para modificar una playa
    private static Playa playaSelected;

    public void preRenderView() {
	if (!FacesContext.getCurrentInstance().isPostback()) {
	    // if (getDireccionBusqueda() == null) {
	    // if (getDireccionBusqueda().trim().isEmpty()) {
	    if (getPlayaResultadoBusqueda() != null) {
		playaResultadoBusqueda.clear();
		playaResultadoBusqueda = null;
	    }
	    // }
	    // }
	}
    }

    public void buscarPlaya() {

	if (null != getDireccionBusqueda() && !getDireccionBusqueda().trim().isEmpty()) {
	    try {

		latLonUtil = new LatitudlongitudUtil();
		// GeoposicionDePlaya
		respuesta = latLonUtil.getLocationFromAddress(getDireccionBusqueda().trim() + ", Cordoba, Argentina");
		coordenadas = respuesta.toString();

		playaResultadoBusqueda = new ArrayList<Playa>();
		for (Playa playaAux : getPlayaList()) {
		    Double comparacion = playaAux.getDistanceFrom(respuesta.getLatitud(), respuesta.getLongitud());
		    if (comparacion < getDistancia() && playaAux.getEstado().getId() == 2) {
			playaResultadoBusqueda.add(playaAux);
			LatLng coord1 = new LatLng(playaAux.getLatitud(), playaAux.getLongitud());
			PerfilPlaya perfil = new PerfilPlaya();
			perfil = getPerfilPlayaService().findByPlaya(playaAux);
			WriteImage.getFotoPerfil(perfil);
			advancedModel.addOverlay(new Marker(coord1, playaAux.toString2(), perfil.getNombreFoto(),
				"http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));
		    }
		    LatLng coordenada = new LatLng(respuesta.getLatitud(), respuesta.getLongitud());

		    advancedModel.addOverlay(new Marker(coordenada, "¡Usted está aquí!", null,
			    "http://s3.subirimagenes.com:81/otros/previo/thump_7896462autoicono.jpg"));

		}
		ordenar();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public String getDireccionBusqueda() {
	return direccionBusqueda;
    }

    public void setDireccionBusqueda(String direccionBusqueda) {
	this.direccionBusqueda = direccionBusqueda;
    }

    public List<Playa> getPlayaResultadoBusqueda() {
	return playaResultadoBusqueda;
    }

    public void setPlayaResultadoBusqueda(List<Playa> playaResultadoBusqueda) {
	BusquedaPlayasManagedBean.playaResultadoBusqueda = playaResultadoBusqueda;
    }

    public Integer getDistancia() {
	return distancia;
    }

    public void setDistancia(Integer distancia) {
	this.distancia = distancia;
    }

    public List<Playa> getPlayaList() {
	playaList = new ArrayList<Playa>();
	playaList.addAll(getPlayaService().findAll());
	return playaList;
    }

    public void setPlayaList(List<Playa> playaList) {
	this.playaList = playaList;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setSimpleModel(MapModel simpleModel) {
	this.simpleModel = simpleModel;
    }

    public MapModel getSimpleModel() {
	return simpleModel;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public MapModel getAdvancedModel() {
	return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
	marker = (Marker) event.getOverlay();
    }

    public Marker getMarker() {
	return marker;
    }

    class Comparar implements Comparator<Playa> {
	public int compare(Playa p1, Playa p2) {
	    Double comparacion1 = p1.getDistanceFrom(respuesta.getLatitud(), respuesta.getLongitud());
	    Double comparacion2 = p2.getDistanceFrom(respuesta.getLatitud(), respuesta.getLongitud());

	    return comparacion1.compareTo(comparacion2);
	}

    }

    public void ordenar() {
	List<Playa> playas = playaResultadoBusqueda;
	Collections.sort(playas, new Comparar());

    }

}
