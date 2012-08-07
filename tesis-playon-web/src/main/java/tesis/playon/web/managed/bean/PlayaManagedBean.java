package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.map.OverlaySelectEvent;
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
import tesis.playon.web.service.IRolUsuarioService;
import tesis.playon.web.service.IRolesPorUsuarioService;
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

    private static final String LISTA_PLAYAS_PENDIENTES = "playaspendienteslist";

    private static final String SOLICITUD_PLAYA_END = "solicitudplayaend";

    private static final String ERROR = "error";

    private MapModel simpleModel;
    private final MapModel advancedModel = new DefaultMapModel();
    private Marker marker;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    @ManagedProperty(value = "#{RolUsuarioService}")
    IRolUsuarioService rolUsuarioService;

    @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    List<Playa> playaList;

    List<Playa> playaspendientesList;

    private static List<Playa> playaResultadoBusqueda;

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

    private String telefono;

    private String emailPlaya;

    @SuppressWarnings("unused")
    private SelectItem[] barriosOptions;

    private EstadoPlaya estado;

    @SuppressWarnings("unused")
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

    private Integer distancia = 25;

    private GeoposicionDePlaya respuesta;

    private String coordenadas;

    // para modificar una playa
    private static Playa playaSelected;

    public String addPlayaAdmin() {
	try {
	    EstadoPlaya estado = new EstadoPlaya();
	    estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");

	    Usuario usuario = addUsuario();

	    Playa playa = new Playa();
	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstadia(getEstadia());
	    playa.setEstado(estado);
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmailPlaya());

	    getPlayaService().save(playa);

	    usuario.setPlaya(playa);
	    getUsuarioService().save(usuario);

//	    RolUsuario rol = getRolUsuarioService().findByNombreRolUsuario("ROLE_PLAYA_GERENTE");
//	    RolesPorUsuario rp = new RolesPorUsuario(usuario, rol);
//	    getRolesPorUsuarioService().save(rp);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente : "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return LISTA_PLAYAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar: "
		    + playa.getNombreComercial(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String addSolicitudPlaya() {
	try {
	    EstadoPlaya estado = new EstadoPlaya();
	    estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");

	    Usuario usuario = addUsuario();

	    Playa playa = new Playa();
	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstadia(getEstadia());
	    playa.setEstado(estado);
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmailPlaya());

	    getPlayaService().save(playa);

	    usuario.setPlaya(playa);
	    getUsuarioService().save(usuario);

//	    RolUsuario rol = getRolUsuarioService().findByNombreRolUsuario("ROLE_PLAYA_GERENTE");
//	    RolesPorUsuario rp = new RolesPorUsuario(usuario, rol);
//	    getRolesPorUsuarioService().save(rp);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente : "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return SOLICITUD_PLAYA_END;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar: "
		    + playa.getNombreComercial(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
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
	    return usuario;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String deletePlayaAdmin(Playa playa) {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("De Baja");

	    playa.setEstado(estado);

	    getPlayaService().update(playa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja la playa: "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_PLAYAS;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja la playa: " + playa.getNombreComercial(),
		    "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String deletePlayaAuditoria(Playa playa) {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("De Baja");

	    playa.setEstado(estado);

	    getPlayaService().update(playa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja la playa: "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_PLAYAS_PENDIENTES;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja la playa: " + playa.getNombreComercial(),
		    "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String rejectPlayaAuditoria(Playa playa) {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("Rechazada");

	    playa.setEstado(estado);

	    getPlayaService().update(playa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se rechazó la playa: "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_PLAYAS_PENDIENTES;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo rechazar la playa: " + playa.getNombreComercial(),
		    "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String approvePlayaAuditoria(Playa playa) {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("Aprobada");

	    playa.setEstado(estado);

	    getPlayaService().update(playa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se aprobó la playa: "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_PLAYAS_PENDIENTES;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo aprobar la playa: "
		    + playa.getNombreComercial(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String updatePlayaAdmin(Playa playa) {
	playaSelected = playa;
	return "playaeditadmin";
    }

    public String updatePlayaAdmin() {
	try {
	    getPlayaService().update(playaSelected);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, playaSelected.getNombreComercial()
		    + " se modificó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_PLAYAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + playaSelected.getNombreComercial() + " no se pudo modificar", "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String modificarPlayaAdmin(Playa playon) {
	playaSelected = playon;
	return "playaeditadmin";
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
	this.setTelefono(null);
	this.setEmailPlaya(null);
	PlayaManagedBean.playaSelected = null;
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
	this.setTelefono(null);
	this.setEmailPlaya(null);

	// Atributos del encargado
	this.setNombreUser("");
	this.setApellido("");
	this.setEmail("");
	this.setNroDoc(0);
	this.setPassword("");
	this.setNombre("");
	this.setTipoDoc(null);
	this.setPlaya(null);
    }

    public void buscarPlaya() {
	try {

	    latLonUtil = new LatitudlongitudUtil();
	    // GeoposicionDePlaya
	    respuesta = latLonUtil.getLocationFromAddress(getDireccionBusqueda() + ", Cordoba, Argentina");
	    coordenadas = respuesta.toString();

	    playaResultadoBusqueda = new ArrayList<Playa>();
	    for (Playa playaAux : getPlayaList()) {
		Double comparacion = playaAux.getDistanceFrom(respuesta.getLatitud(), respuesta.getLongitud());
		if (comparacion < getDistancia() && playaAux.getEstado().getId()==2) {
		    playaResultadoBusqueda.add(playaAux);
		    LatLng coord1 = new LatLng(playaAux.getLatitud(), playaAux.getLongitud());
		    advancedModel.addOverlay(new Marker(coord1, playaAux.toString2(), null,
			    "http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));
		}
		LatLng coordenada = new LatLng(respuesta.getLatitud(), respuesta.getLongitud());
		advancedModel.addOverlay(new Marker(coordenada, "¡Usted está aquí!", null,
			"http://s3.subirimagenes.com:81/otros/previo/thump_7896462autoicono.jpg"));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
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

    public IRolUsuarioService getRolUsuarioService() {
	return rolUsuarioService;
    }

    public void setRolUsuarioService(IRolUsuarioService rolUsuarioService) {
	this.rolUsuarioService = rolUsuarioService;
    }

    public IRolesPorUsuarioService getRolesPorUsuarioService() {
	return rolesPorUsuarioService;
    }

    public void setRolesPorUsuarioService(IRolesPorUsuarioService rolesPorUsuarioService) {
	this.rolesPorUsuarioService = rolesPorUsuarioService;
    }

    public List<Playa> getPlayaList() {
	playaList = new ArrayList<Playa>();
	playaList.addAll(getPlayaService().findAll());
	return playaList;
    }

    public void setPlayaList(List<Playa> playaList) {
	this.playaList = playaList;
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

    public List<Playa> getPlayaResultadoBusqueda() {
	return playaResultadoBusqueda;
    }

    public void setPlayaResultadoBusqueda(List<Playa> playaResultadoBusqueda) {
	PlayaManagedBean.playaResultadoBusqueda = playaResultadoBusqueda;
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

    public Integer getDistancia() {
	return distancia;
    }

    public void setDistancia(Integer distancia) {
	this.distancia = distancia;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
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

    public List<Playa> getFilteredPlayas() {
	return filteredPlayas;
    }

    public void setFilteredPlayas(List<Playa> filteredPlayas) {
	this.filteredPlayas = filteredPlayas;
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

    public void setSimpleModel(MapModel simpleModel) {
	this.simpleModel = simpleModel;
    }

    public SelectItem[] getBarriosOptions() {
	List<Barrio> barrios = new ArrayList<Barrio>();
	barrios.addAll(getBarrioService().findAll());
	barriosOptions = new SelectItem[barrios.size() + 1];
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
	estadosOptions = new SelectItem[estados.size() + 1];
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

    public Playa getPlayaSelected() {
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	PlayaManagedBean.playaSelected = playaSelected;
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
}