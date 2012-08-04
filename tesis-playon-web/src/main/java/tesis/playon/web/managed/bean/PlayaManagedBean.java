package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.LatitudlongitudUtil;
import tesis.playon.web.util.LatitudlongitudUtil.GeoposicionDePlaya;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "playaMB")
@RequestScoped
public class PlayaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PLAYAS = "playalist";

    // private static final String LISTA_PLAYAS_PENDIENTES = "playaspendienteslist";

    private static final String SOLICITUD_PLAYA_END = "solicitudplayaend";

    private static final String ERROR = "error";

    private MapModel simpleModel;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    List<Playa> playaList;

    List<Playa> playaspendientesList;

    static List<Playa> playaResultadoBusqueda;

    private List<Playa> filteredPlayas;

    LatitudlongitudUtil latLonUtil;

    // Atributos de las playas.
    private String cuit;

    private Integer disponibilidad;

    private String direccionBusqueda;

    private String domicilio;

    private String nombreComercial;

    private String razonSocial;

    private Barrio barrio;

    private SelectItem[] barriosOptions;

    private EstadoPlaya estado;

    private SelectItem[] estadosOptions;

    private Estadia estadia;

    // Atributos del encargado
    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

    private Playa playa;

    private Double distancia = (double) 25;

    private  GeoposicionDePlaya respuesta;

    private  String coordenadas;

    
    public String addPlaya() {
	try {
	    EstadoPlaya estado = new EstadoPlaya();
	    estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");

	    Playa playa = new Playa();
	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstadia(getEstadia());
	    playa.setEstado(estado);
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());
	    getPlayaService().save(playa);
	    return LISTA_PLAYAS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String addSolicitudPlaya() {
	try {
	    EstadoPlaya estado = new EstadoPlaya();
	    estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");

	    Playa playa = new Playa();

	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstadia(getEstadia());
	    playa.setEstado(estado);
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());

	    getPlayaService().save(playa);
	    setPlaya(playa);
	    Usuario usuario = addUsuario();

	    return SOLICITUD_PLAYA_END;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public Usuario addUsuario() {
	try {
	    Usuario usuario = new Usuario();
	    usuario.setNombre(getNombre());
	    usuario.setApellido(getApellido());
	    usuario.setEmail(getEmail());
	    usuario.setNroDoc(getNroDoc());
	    usuario.setPassword(getPassword());
	    usuario.setNombreUser(getNombreUser());
	    usuario.setTipoDoc(getTipoDoc());
	    usuario.setPlaya(getPlaya());
	    getUsuarioService().save(usuario);
	    return usuario;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void buscarPlaya() {

	try {
	    simpleModel = new DefaultMapModel();
	    latLonUtil = new LatitudlongitudUtil();
	    // GeoposicionDePlaya
	    respuesta = latLonUtil.getLocationFromAddress(getDireccionBusqueda() + ", Cordoba, Argentina");
	    coordenadas=respuesta.toString();
	    
	    playaResultadoBusqueda = new ArrayList<Playa>();
	    for (Playa playaAux : getPlayaList()) {
		Double comparacion = playaAux.getDistanceFrom(respuesta.getLatitud(), respuesta.getLongitud());
		if (comparacion < getDistancia()) {
		    playaResultadoBusqueda.add(playaAux);

		    // Shared coordinates
		    LatLng coord1 = new LatLng(playaAux.getLatitud(), playaAux.getLongitud());
		    // Basic marker
		    simpleModel.addOverlay(new Marker(coord1, playaAux.getNombreComercial(), null,
			    "http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));

		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void deleteUsuario(Usuario usuario) {
	getUsuarioService().delete(usuario);
    }

    public void updateUsuario(Usuario usuario) {
	getUsuarioService().update(usuario);
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public void deletePlaya(Playa playa) {
	getPlayaService().delete(playa);
    }

    public void updatePlaya(Playa playa) {
	getPlayaService().update(playa);
    }

    public void reset() {
	this.setBarrio(null);
	this.setCuit("");
	this.setDisponibilidad(0);
	this.setDomicilio("");
	this.setEstadia(null);
	this.setEstado(null);
	this.setNombreComercial("");
	this.setRazonSocial("");
    }

    public void solicitudReset() {
	// Atributos de la playa
	this.setCuit("");
	this.setDisponibilidad(0);
	this.setDireccionBusqueda("");
	this.setDomicilio("");
	this.setNombreComercial("");
	this.setRazonSocial("");
	this.setBarrio(null);
	this.setEstado(null);
	this.setEstadia(null);

	// Atributos del encargado
	this.setNombreUser("");
	this.setApellido("");
	this.setEmail("");
	this.setNroDoc(0);
	this.setPassword("");
	this.setNombre("");
	this.setTipoDoc(null);
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IEstadoPlayaService getEstadoPlayaService() {
	return estadoPlayaService;
    }

    public void setEstadoPlayaService(IEstadoPlayaService estadoPlayaService) {
	this.estadoPlayaService = estadoPlayaService;
    }

    public IBarrioService getBarrioService() {
	return barrioService;
    }

    public void setBarrioService(IBarrioService barrioService) {
	this.barrioService = barrioService;
    }

    public List<Playa> getPlayaList() {
	playaList = new ArrayList<Playa>();
	playaList.addAll(getPlayaService().findAll());
	return playaList;
    }

    public List<Playa> getPlayaspendientesList() {
	playaspendientesList = new ArrayList<Playa>();
	EstadoPlaya estado = new EstadoPlaya();
	estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");
	playaspendientesList.addAll(getPlayaService().findPlayasPendientes(estado));
	return playaspendientesList;
    }

    public void setPlayaspendientesList(List<Playa> playaspendientesList) {

	this.playaspendientesList = playaspendientesList;
    }

    public void setPlayaList(List<Playa> playaList) {
	this.playaList = playaList;
    }

    public List<Playa> getPlayaResultadoBusqueda() {
	return playaResultadoBusqueda;
    }

    public void setPlayaResultadoBusqueda(List<Playa> playaResultadoBusqueda) {
	this.playaResultadoBusqueda = playaResultadoBusqueda;
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

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
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

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public String getDireccionBusqueda() {
	return direccionBusqueda;
    }

    public void setDireccionBusqueda(String direccionBusqueda) {
	this.direccionBusqueda = direccionBusqueda;
    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Integer getNroDoc() {
	return nroDoc;
    }

    public void setNroDoc(Integer nroDoc) {
	this.nroDoc = nroDoc;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getNombreUser() {
	return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
	this.nombreUser = nombreUser;
    }

    public TipoDoc getTipoDoc() {
	return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
	this.tipoDoc = tipoDoc;
    }

    public LatitudlongitudUtil getLatLonUtil() {
	return latLonUtil;
    }

    public void setLatLonUtil(LatitudlongitudUtil latLonUtil) {
	this.latLonUtil = latLonUtil;
    }

    public Double getDistancia() {
	return distancia;
    }

    public void setDistancia(Double distancia) {
	this.distancia = distancia;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public List<Playa> getFilteredPlayas() {
	return filteredPlayas;
    }

    public void setFilteredPlayas(List<Playa> filteredPlayas) {
	this.filteredPlayas = filteredPlayas;
    }
    
    public  GeoposicionDePlaya getRespuesta() {
	return respuesta;
    }

    public  void setRespuesta(GeoposicionDePlaya respuesta) {
	this.respuesta = respuesta;
    }

    public String getCoordenadas() {
	return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
	this.coordenadas = coordenadas;
    }

    public void setSimpleModel(MapModel simpleModel) {
	this.simpleModel = simpleModel;
    }


    public SelectItem[] getBarriosOptions() {
	List<Barrio> barrios = new ArrayList<Barrio>();
	barrios.addAll(getBarrioService().findAll());
	SelectItem[] options = new SelectItem[barrios.size() + 1];
	options[0] = new SelectItem("", "Todos");

	for (int i = 0; i < barrios.size(); i++) {
	    options[i + 1] = new SelectItem(barrios.get(i), barrios.get(i).getNombre());
	}
	return options;
    }

    public void setBarriosOptions(SelectItem[] barriosOptions) {
	this.barriosOptions = barriosOptions;
    }

    public SelectItem[] getEstadosOptions() {
	List<EstadoPlaya> estados = new ArrayList<EstadoPlaya>();
	estados.addAll(getEstadoPlayaService().findAll());
	SelectItem[] options = new SelectItem[estados.size() + 1];
	options[0] = new SelectItem("", "Todos");

	for (int i = 0; i < estados.size(); i++) {
	    options[i + 1] = new SelectItem(estados.get(i), estados.get(i).getNombre());
	}
	return options;
    }

    public void setEstadosOptions(SelectItem[] estadosOptions) {
	this.estadosOptions = estadosOptions;
    }

    public MapModel getSimpleModel() {
	return simpleModel;
    }

}