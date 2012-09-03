/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Mail;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "auditoriaMB")
@ViewScoped
public class AuditoriaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String ERROR = "error";

    private NotificadorUtil notificador;

    private Mail mail;

    private String asunto;

    private String mensaje;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    static Playa playaSeleccionada;

    private String cuit;

    private Integer disponibilidad;

    private String direccionBusqueda;

    private String domicilio;

    private String nombreComercial;

    private String razonSocial;

    private Barrio barrio;

    private String telefono;

    private String emailPlaya;

    List<Playa> playasPendientesList;

    List<Playa> playasRechazadasList;

    private List<Playa> filteredPlayas;

    private static String previusPage;

    @SuppressWarnings("unused")
    private SelectItem[] barriosOptions;

    @SuppressWarnings("unused")
    private SelectItem[] estadosOptions;

    public String updatePlayaAuditor() {
	try {
	    if (playaSeleccionada.getEstado().getNombre().equals("De Baja")) {
		List<Usuario> userList = getUsuarioService().findByPlaya(playaSeleccionada);
		for (Usuario usuario : userList) {
		    usuario.setEnable(new Boolean(false));
		    getUsuarioService().update(usuario);
		}
	    }
	    getPlayaService().update(playaSeleccionada);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, playaSeleccionada.getNombreComercial()
		    + " se modificó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return previusPage;

	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + playaSeleccionada.getNombreComercial() + " no se pudo modificar",
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void rejectPlayaAuditoria(Playa playa) {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("Rechazada");
	    playa.setEstado(estado);
	    getPlayaService().update(playa);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se rechazó la playa: "
		    + playa.getNombreComercial(), "");
	    notificador = new NotificadorUtil();
	    mail = new Mail();
	    mail.setDestinatario(getEmailPlaya());
	    asunto = "Su solicitud ha sido rechazada";
	    mail.setAsunto(asunto);
	    mensaje = "Los datos de su solicitud de playa no son correctos o verdaderos, por lo tanto hemos rechazado su solicitud,"
		    + " comuniquese con el equipo de Playon mediante el siguiente link http://localhost:8080/tesis-playon-web/contact.html";
	    mail.setMensaje(mensaje);
	    
	    notificador.enviar(mail);
	    
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo rechazar la playa: " + playa.getNombreComercial(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    public void approvePlayaAuditoria(Playa playa) {
	try {
	    

	    		  
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("Aprobada");

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
	    mail=new Mail();
	    notificador=new NotificadorUtil();
	    asunto="Felicitaciones, su playa ya es parte de PLAYON!";
	    mensaje="Mediante este mensaje, le confirmamos que la playa de estacionamiento" + getNombreComercial() + " ha sido aprobada" + 
	    "para formar parte de la Red de Playas Playon";
	    mail.setAsunto(asunto);
	    mail.setMensaje(mensaje);
	    mail.setDestinatario(getEmailPlaya());
	    notificador.enviar(mail);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se aprobó la playa: "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo aprobar la playa: "
		    + playa.getNombreComercial(), "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    public String returnPage() {
	return previusPage;
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

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IRolesPorUsuarioService getRolesPorUsuarioService() {
	return rolesPorUsuarioService;
    }

    public void setRolesPorUsuarioService(IRolesPorUsuarioService rolesPorUsuarioService) {
	this.rolesPorUsuarioService = rolesPorUsuarioService;
    }

    public List<Playa> getPlayasPendientesList() {
	playasPendientesList = new ArrayList<Playa>();
	EstadoPlaya estado = new EstadoPlaya();
	estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");
	playasPendientesList.addAll(getPlayaService().findByEstado(estado));
	return playasPendientesList;
    }

    public void setPlayasPendientesList(List<Playa> playasPendientesList) {
	this.playasPendientesList = playasPendientesList;
    }

    public List<Playa> getPlayasRechazadasList() {
	playasRechazadasList = new ArrayList<Playa>();
	EstadoPlaya estado = new EstadoPlaya();
	estado = getEstadoPlayaService().findByNombreEstadoPlaya("Rechazada");
	playasRechazadasList.addAll(getPlayaService().findByEstado(estado));
	return playasRechazadasList;
    }

    public void setPlayasRechazadasList(List<Playa> playasRechazadasList) {
	this.playasRechazadasList = playasRechazadasList;
    }

    public List<Playa> getFilteredPlayas() {
	return filteredPlayas;
    }

    public void setFilteredPlayas(List<Playa> filteredPlayas) {
	this.filteredPlayas = filteredPlayas;
    }

    public Playa getPlayaSeleccionada() {

	return playaSeleccionada;
    }

    public void setPlayaSeleccionada(Playa playaSeleccionada) {
	AuditoriaManagedBean.playaSeleccionada = playaSeleccionada;
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

    public String getDireccionBusqueda() {
	return direccionBusqueda;
    }

    public void setDireccionBusqueda(String direccionBusqueda) {
	this.direccionBusqueda = direccionBusqueda;
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

    public String getPreviusPage() {
	return previusPage;
    }

    public void setPreviusPage(String previusPage) {
	AuditoriaManagedBean.previusPage = previusPage;
    }
}