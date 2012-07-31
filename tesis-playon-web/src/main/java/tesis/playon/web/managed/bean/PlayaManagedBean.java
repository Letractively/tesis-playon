/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.LatitudlongitudUtil;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "playaMB")
@RequestScoped
public class PlayaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PLAYAS = "playalist";
    
    private static final String LISTA_PLAYAS_PENDIENTES = "playaspendientes";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    List<Playa> playaList;

    // Atributos de las playas.
    private String cuit;

    private Integer disponibilidad;

    private String direccionBusqueda;

    private String domicilio;

    private String nombreComercial;

    private String razonSocial;

    private Barrio barrio;

    private EstadoPlaya estado;

    private Estadia estadia;

    // Atributos del encargado
    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

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
	    Usuario usuario = addUsuario();

	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstadia(getEstadia());
	    playa.setEstado(estado);
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());

	    getPlayaService().save(playa);
	    getUsuarioService().save(usuario);

	    return LISTA_PLAYAS;
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
	    getUsuarioService().save(usuario);
	    return usuario;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return null;
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

    public void buscarPlaya() {

	try {
	    String respuesta = LatitudlongitudUtil.getLocationFromAddress(getDireccionBusqueda()
		    + ", Córdoba, Argentina");
	    System.out.println("\n\n\n" + respuesta + "\n\n\n");
	} catch (Exception e) {
	    e.printStackTrace();
	}
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
	this.setApellido("");
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

    public List<Playa> getPlayaList() {
	playaList = new ArrayList<Playa>();
	playaList.addAll(getPlayaService().findAll());
	return playaList;
    }
    
    public List<Playa> getPlayasPendientes() {
	playaList = new ArrayList<Playa>();
	playaList.addAll(getPlayaService().findPlayasPendientes(getEstado()));
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

}