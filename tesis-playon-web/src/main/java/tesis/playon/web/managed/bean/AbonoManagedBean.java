/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IAbonoService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoEstadiaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "abonoMB")
@RequestScoped
public class AbonoManagedBean implements Serializable {

    private static final long serialVersionUID = 7775997635327144533L;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{VehiculoService}")
    IVehiculoService vehiculoService;

    @ManagedProperty(value = "#{TipoEstadiaService}")
    ITipoEstadiaService tipoEstadiaService;

    @ManagedProperty(value = "#{AbonoService}")
    IAbonoService abonoService;

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    private Date fechaDesde;

    private Date fechaHasta;

    private Tarifa tarifa;

    private Cliente cliente;

    private Playa playa;

    private String patente;

    private Promocion promocion;

    private Vehiculo vehiculo;

    private List<Promocion> promocionesDisponibles;

    private Usuario usuarioLoggeadoPlaya;

    private Usuario usuarioLoggeadoCliente;

    private Playa playaLoggeada;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    usuarioLoggeadoPlaya = user;
	    playaLoggeada = user.getPlaya();
	}
	if (user != null && user.getPlaya() == null) {
	    usuarioLoggeadoCliente = user;
	}
    }

    public void validateVehiculo(FacesContext context, UIComponent component, Object value) {
	String patente = value.toString();
	if (patente != null) {
	    vehiculo = getVehiculoService().findByPatenteVehiculo(patente.toUpperCase());
	    if (vehiculo != null) {
		TipoEstadia tipoEstadia = getTipoEstadiaService().findByNombreTipoEstadia("Por Mes");
		CategoriaVehiculo categoriaVehiculo = vehiculo.getModeloVehiculo().getCategoriaVehiculo();
		tarifa = getTarifaService().findTarifaVigenteByPlayaAndCategoriaAndTipoEstadia(playaLoggeada,
			categoriaVehiculo, tipoEstadia);
		if (tarifa == null) {
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe tarifa mensual "
			    +"para la categoría "+ tipoEstadia, null);
		    throw new ValidatorException(message);
		}
	    }
	    else{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe el vehiculo "
			    + vehiculo.getPatente(), null);
		    throw new ValidatorException(message);
	    }
	}
    }

    public Date getFechaDesde() {
	return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
	return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public ITarifaService getTarifaService() {
	return tarifaService;
    }

    public void setTarifaService(ITarifaService tarifaService) {
	this.tarifaService = tarifaService;
    }

    public IVehiculoService getVehiculoService() {
	return vehiculoService;
    }

    public void setVehiculoService(IVehiculoService vehiculoService) {
	this.vehiculoService = vehiculoService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public ITipoEstadiaService getTipoEstadiaService() {
	return tipoEstadiaService;
    }

    public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
	this.tipoEstadiaService = tipoEstadiaService;
    }

    public IAbonoService getAbonoService() {
	return abonoService;
    }

    public void setAbonoService(IAbonoService abonoService) {
	this.abonoService = abonoService;
    }

    public Promocion getPromocion() {
	return promocion;
    }

    public void setPromocion(Promocion promocion) {
	this.promocion = promocion;
    }

    public List<Promocion> getPromocionesDisponibles() {
	return promocionesDisponibles;
    }

    public void setPromocionesDisponibles(List<Promocion> promocionesDisponibles) {
	this.promocionesDisponibles = promocionesDisponibles;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public Usuario getUsuarioLoggeadoPlaya() {
	return usuarioLoggeadoPlaya;
    }

    public void setUsuarioLoggeadoPlaya(Usuario usuarioLoggeadoPlaya) {
	this.usuarioLoggeadoPlaya = usuarioLoggeadoPlaya;
    }

    public Usuario getUsuarioLoggeadoCliente() {
	return usuarioLoggeadoCliente;
    }

    public void setUsuarioLoggeadoCliente(Usuario usuarioLoggeadoCliente) {
	this.usuarioLoggeadoCliente = usuarioLoggeadoCliente;
    }

    public Playa getPlayaLoggeada() {
	return playaLoggeada;
    }

    public void setPlayaLoggeada(Playa playaLoggeada) {
	this.playaLoggeada = playaLoggeada;
    }

}
