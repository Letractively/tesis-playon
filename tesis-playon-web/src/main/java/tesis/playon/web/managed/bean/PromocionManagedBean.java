package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IEstadoPromocionService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IPromocionService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * 
 * @author pablo
 * 
 */
@ManagedBean(name = "promocionMB")
public class PromocionManagedBean implements Serializable {

    private static final long serialVersionUID = 6826797453747733748L;

    @ManagedProperty(value = "#{EstadoPromocionService}")
    IEstadoPromocionService estadoPromocionService;

    @ManagedProperty(value = "#{PromocionService}")
    IPromocionService promocionService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    // Atributtes Promocion

    private String descripcion;

    private Float descuento;

    private Date fechaAlta;

    private Date fechaInicio;

    private Date fechaFin;

    private Float montoFijo;

    private Float montoConDescuento;

    private String nombre;

    private static Tarifa tarifa;

    private Playa playa;

    private EstadoPromocion estadoPromocion;
    
    private Date today;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    this.playa = user.getPlaya();
	}
	today= new Date();
    }

    public String addPromocion() {
	Promocion promocion = null;
	
	try {
	    promocion = new Promocion();
	    promocion.setDescripcion(getDescripcion());
	    promocion.setDescuento(getDescuento());
	    promocion.setEstadoPromocion(getEstadoPromocion());
	    promocion.setFechaAlta(new Date());
	    promocion.setFechaFin(getFechaFin());
	    promocion.setFechaInicio(getFechaInicio());
	    promocion.setMontoFijo(getTarifa().getImporte());
	    promocion.setNombre(getNombre());
	    promocion.setPlaya(getPlaya());
	    promocion.setTarifa(getTarifa());
	    
	    getPromocionService().save(promocion);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró exitosamente la promoción: ", promocion.getNombre());
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "promocionaddend";
	}
	catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo registrar la promoción, Disculpe las molestias ocacionadas.", null);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return null;
    }

    public String updatePromocion() {
	return null;
    }

    public String deletePromocion() {
	return null;
    }

    public void calcularDescuento() {
	this.montoConDescuento = tarifa.getImporte() *(1- (descuento / 100));
    }

    public void seleccionarTarifa(Tarifa tarifaParameter) {
	if (tarifaParameter != null) {
	    tarifa = tarifaParameter;
	    this.montoFijo = tarifaParameter.getImporte();
	}
    }

    public void validateDateInicial(FacesContext context, UIComponent component, Object value) {
	Date dateInicial = (Date) value;
	if (dateInicial != null) {
	    this.setFechaInicio(dateInicial);
	}
    }
    
    public void validateDate(FacesContext context, UIComponent component, Object value) {
	Date dateFin = (Date) value;
	if (getFechaInicio() != null) {
	    if(dateFin.before(getFechaInicio())){
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "La fecha de finalización debe ser mayor a la fecha de inicio.", null);
	    throw new ValidatorException(message);
	    }
	}
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

    public ITarifaService getTarifaService() {
	return tarifaService;
    }

    public void setTarifaService(ITarifaService tarifaService) {
	this.tarifaService = tarifaService;
    }

    public IEstadoPromocionService getEstadoPromocionService() {
	return estadoPromocionService;
    }

    public void setEstadoPromocionService(IEstadoPromocionService estadoPromocionService) {
	this.estadoPromocionService = estadoPromocionService;
    }

    // ============================ GETTER && SETTER =======================================

    public IPromocionService getPromocionService() {
	return promocionService;
    }

    public void setPromocionService(IPromocionService promocionService) {
	this.promocionService = promocionService;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public Float getDescuento() {
	return descuento;
    }

    public void setDescuento(Float descuento) {
	this.descuento = descuento;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }

    public Date getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
	return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
    }

    public Float getMontoFijo() {
	return montoFijo;
    }

    public void setMontoFijo(Float montoFijo) {
	this.montoFijo = montoFijo;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	PromocionManagedBean.tarifa = tarifa;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public EstadoPromocion getEstadoPromocion() {
	return estadoPromocion;
    }

    public void setEstadoPromocion(EstadoPromocion estadoPromocion) {
	this.estadoPromocion = estadoPromocion;
    }

    public Float getMontoConDescuento() {
	return montoConDescuento;
    }

    public void setMontoConDescuento(Float montoConDescuento) {
	this.montoConDescuento = montoConDescuento;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}