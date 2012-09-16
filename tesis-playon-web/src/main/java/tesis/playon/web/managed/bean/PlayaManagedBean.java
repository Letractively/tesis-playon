package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Mail;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IEstadiaService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IRolUsuarioService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "playaMB")
@ViewScoped
public class PlayaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PLAYAS = "playalist";

    private static final String SOLICITUD_PLAYA_END = "solicitudplayaend";

    private static final String ERROR = "error";

    private MapModel simpleModel;
    private final MapModel advancedModel = new DefaultMapModel();
    private Marker marker;
    private Mail mail;
    private NotificadorUtil notificador;

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

    @ManagedProperty(value = "#{EstadiaService}")
    IEstadiaService estadiaService;

    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;
    
    @ManagedProperty(value = "#{CargoEmpleadoService}")
    ICargoEmpleadoService cargoEmpleadoService;
    
    @ManagedProperty(value = "#{EmpleadoService}")
    IEmpleadoService empleadoService;

    List<Playa> playaList;

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

    // Atributos del encargado
    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

    private Playa playa;

    // para modificar una playa
    private static Playa playaSelected;

    public String addPlayaAdmin() {
	try {

	    Playa playa = new Playa();
	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstado(getEstado());
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmailPlaya());

	    getPlayaService().save(playa);

	    PerfilPlaya perfil = new PerfilPlaya();
	    perfil.setPlaya(playa);

	    getPerfilPlayaService().save(perfil);

	    Estadia estadia = new Estadia(playa);
	    getEstadiaService().save(estadia);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente : "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    reset();
	    return LISTA_PLAYAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar: "
		    + playa.getNombreComercial(), "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    reset();
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String addSolicitudPlaya() {
	try {
	    EstadoPlaya estado = new EstadoPlaya();
	    estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");

	    Usuario usuario = addUsuario();

	    CargoEmpleado cargo = getCargoEmpleadoService().findByNombreCargo("Gerente General");
	    
	    Playa playa = new Playa();
	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstado(estado);
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmailPlaya());

	    getPlayaService().save(playa);

	    PerfilPlaya perfil = new PerfilPlaya();
	    perfil.setPlaya(playa);
	    // perfil.setFotoPerfil(fotoPerfil);

	    getPerfilPlayaService().save(perfil);

	    Estadia estadia = new Estadia(playa);
	    getEstadiaService().save(estadia);

	    usuario.setPlaya(playa);
	    getUsuarioService().save(usuario);
	    
	    Empleado empleado = new Empleado();
	    empleado.setUsuario(usuario);
	    empleado.setLegajo(new Integer(1001));
	    empleado.setCargoEmpleado(cargo);
	    getEmpleadoService().save(empleado);

	    RolesPorUsuario rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_PLAYA_GERENTE");
	    getRolesPorUsuarioService().save(rp);

	    String asunto = "Notoficación equipo de Playon";
	    String mensaje = "Su solicitud esta en el área de auditoria, en breve nos comunicaremos con usted,\n\n muchas gracias";
	    mail = new Mail();
	    mail.setAsunto(asunto);
	    mail.setMensaje(mensaje);
	    mail.setDestinatario(getEmail());
	    notificador = new NotificadorUtil();
	    notificador.enviar(mail);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente : "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    reset();

	    return SOLICITUD_PLAYA_END;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar: "
		    + playa.getNombreComercial(), "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    reset();
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
	    usuario.setEnable(new Boolean(false));
	    return usuario;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void deletePlayaAdmin(Playa playa) {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("De Baja");

	    playa.setEstado(estado);

	    List<Usuario> userList = getUsuarioService().findByPlaya(playa);

	    for (Usuario usuario : userList) {

		RolesPorUsuario rolUsuario = getRolesPorUsuarioService().findByNombreUsuario(usuario.getNombreUser());

		if (rolUsuario.getRol().equals("ROLE_PLAYA_GERENTE")) {
		    usuario.setEnable(new Boolean(true));
		    getUsuarioService().update(usuario);
		}
	    }

	    getPlayaService().update(playa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja la playa: "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja la playa: " + playa.getNombreComercial(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    // NO USAR. HAY Q CORREGIRLE ALGUNAS COSAS ---> TOMAR COMO REFERENCIA LA DE ARRIBA
    public void deletePlayaAuditoria(Playa playa) {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("De Baja");

	    playa.setEstado(estado);

	    getPlayaService().update(playa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja la playa: "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja la playa: " + playa.getNombreComercial(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    public String updatePlayaAdmin() {
	try {
	    if (playaSelected.getEstado().getNombre().equals("De Baja")) {
		List<Usuario> userList = getUsuarioService().findByPlaya(playaSelected);
		for (Usuario usuario : userList) {
		    usuario.setEnable(new Boolean(false));
		    getUsuarioService().update(usuario);
		}
	    }
	    getPlayaService().update(playaSelected);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, playaSelected.getNombreComercial()
		    + " se modificó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_PLAYAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + playaSelected.getNombreComercial() + " no se pudo modificar", "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void reset() {
	// Atributos de la playa
	this.setCuit("");
	this.setDisponibilidad(0);
	this.setDireccionBusqueda("");
	this.setDomicilio("");
	this.setNombreComercial("");
	this.setRazonSocial("");
	this.setBarrio(null);
	this.setEstado(null);
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

    public IEstadiaService getEstadiaService() {
	return estadiaService;
    }

    public void setEstadiaService(IEstadiaService estadiaService) {
	this.estadiaService = estadiaService;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
	return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
	this.perfilPlayaService = perfilPlayaService;
    }

    public ICargoEmpleadoService getCargoEmpleadoService() {
        return cargoEmpleadoService;
    }

    public void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
        this.cargoEmpleadoService = cargoEmpleadoService;
    }

    public IEmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setEmpleadoService(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public List<Playa> getPlayaList() {
	playaList = new ArrayList<Playa>();
	playaList.addAll(getPlayaService().findAll());
	return playaList;
    }

    public void setPlayaList(List<Playa> playaList) {
	this.playaList = playaList;
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

    public void setSimpleModel(MapModel simpleModel) {
	this.simpleModel = simpleModel;
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