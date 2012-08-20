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
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPlayaService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "auditoriaMB")
@ViewScoped
public class AuditoriaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;
    
    private static final String LISTA_PLAYAS_PENDIENTES = "playaspendienteslist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;
    
    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

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

    @SuppressWarnings("unused")
    private SelectItem[] barriosOptions;

    @SuppressWarnings("unused")
    private SelectItem[] estadosOptions;
    
    public void action(ActionEvent event){
	System.out.println("\n\n\n\n\n\n");
	System.out.println(playaSeleccionada.toString());
	System.out.println("\n\n\n\n\n\n");
	RequestContext.getCurrentInstance().addCallbackParam("currentPlaya", playaSeleccionada);
    }
    
    public String updatePlayaAdmin() {
	try {
	    getPlayaService().update(playaSeleccionada);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, playaSeleccionada.getNombreComercial()
		    + " se modificó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_PLAYAS_PENDIENTES;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + playaSeleccionada.getNombreComercial() + " no se pudo modificar", "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
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
	playasRechazadasList.addAll(getPlayaService().findAll());
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
}
