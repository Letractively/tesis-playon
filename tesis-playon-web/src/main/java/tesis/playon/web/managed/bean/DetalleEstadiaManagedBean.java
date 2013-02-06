/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.model.TipoPago;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.model.TransaccionPlaya;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IAbonoService;
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.ICuentaPlayaService;
import tesis.playon.web.service.IDetalleEstadiaService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IEstadiaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IPromocionService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoEstadiaService;
import tesis.playon.web.service.ITipoPagoService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.ITransaccionPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "detalleEstadiaMB")
@ViewScoped
public class DetalleEstadiaManagedBean implements Serializable {

    private static final long serialVersionUID = 2624234897160641014L;

    @ManagedProperty(value = "#{EmpleadoService}")
    IEmpleadoService empleadoService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{CargoEmpleadoService}")
    ICargoEmpleadoService cargoEmpleadoService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{CuentaPlayaService}")
    ICuentaPlayaService cuentaPlayaService;

    @ManagedProperty(value = "#{VehiculoService}")
    IVehiculoService vehiculoService;

    @ManagedProperty(value = "#{EstadiaService}")
    IEstadiaService estadiaService;

    @ManagedProperty(value = "#{DetalleEstadiaService}")
    IDetalleEstadiaService detalleEstadiaService;

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    @ManagedProperty(value = "#{PromocionService}")
    IPromocionService promocionService;

    @ManagedProperty(value = "#{TipoPagoService}")
    ITipoPagoService tipoPagoService;

    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    @ManagedProperty(value = "#{TransaccionPlayaService}")
    ITransaccionPlayaService transaccionPlayaService;

    @ManagedProperty(value = "#{TipoEstadiaService}")
    ITipoEstadiaService tipoEstadiaService;
    
    @ManagedProperty(value = "#{AbonoService}")
    IAbonoService abonoService;

    private Usuario usuarioLoggeado;

    private Usuario usuarioCliente;

    private Cliente cliente;

    private Empleado empleadoLoggeado;

    private Playa playaLoggeada;

    private CuentaPlaya cuentaPlaya;

    private Estadia estadia;

    private CargoEmpleado cargoEmpleado;

    private List<TipoEstadia> tipoEstadiaList;

    private String patente;

    private Vehiculo vehiculo;

    private DetalleEstadia detalleEstadia;

    private Date fechaIngreso;

    private Time horaIngreso;

    private Date fechaActual;

    @SuppressWarnings("unused")
    private String horaActual;

    private Float importe;

    private List<Tarifa> tarifaPlayaList;

    private List<Promocion> promocionesDisponibles;

    // ======== Variables extraAuxiliares =========

    private boolean saldoPositivo;

    private boolean existeTarifa;

    private boolean existeVehiculo;
    
    private boolean existeAbonoVehiculo;

    private boolean importeCalculado = false;

    private Tarifa tarifaSeleccionada;

    private Promocion promocionSeleccionada;

    private boolean imprimir = true;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	try {
	    usuarioLoggeado = getUsuarioService().findByNombreUsuario(userName);
	    if (usuarioLoggeado != null) {
		empleadoLoggeado = getEmpleadoService().findByUsuario(usuarioLoggeado);
		playaLoggeada = usuarioLoggeado.getPlaya();
		if (playaLoggeada != null) {
		    cuentaPlaya = getCuentaPlayaService().findByPlaya(playaLoggeada);
		    if (cuentaPlaya == null) {
			cuentaPlaya = new CuentaPlaya(playaLoggeada);
			getCuentaPlayaService().save(cuentaPlaya);
		    }

		    estadia = getEstadiaService().findByPlaya(playaLoggeada);
		    if (estadia == null) {
			estadia = new Estadia(playaLoggeada);
			getEstadiaService().save(estadia);
		    }

		    cargoEmpleado = empleadoLoggeado.getCargoEmpleado();
		}
	    }
	    tipoEstadiaList = getTipoEstadiaService().findAll();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void searchVehiculo() {
	String auxPatente = patente.trim();

	try {
	    vehiculo = getVehiculoService().findByPatenteVehiculo(auxPatente);

	    if (vehiculo != null) {
		cliente = vehiculo.getCliente();
		existeAbonoVehiculo = getAbonoService().existeAbonoVehiculo(vehiculo, playaLoggeada, new Date());

		if (cliente != null) {
		    usuarioCliente = cliente.getUsuario();
		    if (cliente.getCuentaCliente() != null) {
			if (cliente.getCuentaCliente().getSaldo() > 0) {
			    saldoPositivo = true;
			} else {
			    saldoPositivo = false;
			}
		    }
		}

		detalleEstadia = getDetalleEstadiaService().findByVehiculoDetalleEstadia(vehiculo);
		if (detalleEstadia != null && !detalleEstadia.getCobrado()) {
		    Calendar calendario = Calendar.getInstance();
		    calendario.setTime(detalleEstadia.getFechaHoraIngreso());
		    int hour = calendario.get(Calendar.HOUR_OF_DAY);
		    int minutes = calendario.get(Calendar.MINUTE);
		    int seconds = calendario.get(Calendar.SECOND);

		    fechaIngreso = detalleEstadia.getFechaHoraIngreso();
		    horaIngreso = Time.valueOf(hour + ":" + minutes + ":" + seconds);

		}
		tarifaPlayaList = getTarifaService().findTarifaVigenteByPlayaAndCategoriaVehiculo(playaLoggeada,
			vehiculo.getModeloVehiculo().getCategoriaVehiculo());

		// promocionesDisponibles = getPromocionService().findByCategoria(
		// vehiculo.getModeloVehiculo().getCategoriaVehiculo(), playaLoggeada);

		if (tarifaPlayaList == null) {
		    setExisteTarifa(false);
		    FacesContext.getCurrentInstance().addMessage(
			    null,
			    new FacesMessage(FacesMessage.SEVERITY_WARN,
				    "No existen tarifas registradas para la categoria: "
					    + vehiculo.getModeloVehiculo().getCategoriaVehiculo(), null));
		} else
		    setExisteTarifa(true);
		setExisteVehiculo(true);

	    } else {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			"No se encontró el vehículo con patente: " + auxPatente, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		setExisteVehiculo(false);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String registrarIngresoAbono(){
	try {
	    // SAVE TO DETTALLEESTADIA
	    Timestamp fechaHoraIngreso = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    detalleEstadia = new DetalleEstadia(estadia, vehiculo, empleadoLoggeado, fechaHoraIngreso, false);
	    detalleEstadia.setTarifa(null);
	    detalleEstadia.setPromocion(null);
	    getDetalleEstadiaService().save(detalleEstadia);

	    // UPDATE DISPONIBILIDAD OF THE PLAYA.
	    Integer disponibilidad = playaLoggeada.getDisponibilidad() - 1;
	    playaLoggeada.setDisponibilidad(disponibilidad);
	    getPlayaService().update(playaLoggeada);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró el ingreso exitosamente del vehiculo con abono mensual con patente: " + patente, null);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    
	    existeAbonoVehiculo=false;
	    
	    return "/playa/ingresoegresovehiculo";
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    public String registrarEgresoAbonado(){
	try{
	Timestamp fechaHoraEgreso= new Timestamp(Calendar.getInstance().getTimeInMillis());
	detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
	detalleEstadia.setCobrado(true);
	getDetalleEstadiaService().update(detalleEstadia);
	
	Integer disponibilidad = playaLoggeada.getDisponibilidad() + 1;
	    playaLoggeada.setDisponibilidad(disponibilidad);
	    getPlayaService().update(playaLoggeada);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró el egreso exitosamente del vehículo con abono patente: " + patente, null);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    
	    existeAbonoVehiculo=false;
	    
	    return "/playa/ingresoegresovehiculo";
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    public String registrarIngresoVehiculo() {
	try {
	    // SAVE TO DETTALLEESTADIA
	    Timestamp fechaHoraIngreso = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    detalleEstadia = new DetalleEstadia(estadia, vehiculo, empleadoLoggeado, fechaHoraIngreso, false);
	    detalleEstadia.setTarifa(tarifaSeleccionada);
	    detalleEstadia.setPromocion(promocionSeleccionada);
	    getDetalleEstadiaService().save(detalleEstadia);

	    // UPDATE DISPONIBILIDAD OF THE PLAYA.
	    Integer disponibilidad = playaLoggeada.getDisponibilidad() - 1;
	    playaLoggeada.setDisponibilidad(disponibilidad);
	    getPlayaService().update(playaLoggeada);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró el ingreso exitosamente del vehiculo patente: " + patente, null);
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "/playa/ingresoegresovehiculo";
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void calcularImporte() {
	TipoEstadia tipoEstadia = detalleEstadia.getTarifa().getTipoEstadia();
	Timestamp fechaHoraEgreso;

	// calculo de importe a pagar
	if ("Por Hora".equals(tipoEstadia.getNombre())) {
	    fechaHoraEgreso = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
	    long diff = detalleEstadia.getFechaHoraEgreso().getTime() - detalleEstadia.getFechaHoraIngreso().getTime();
	    double diferenciaEnHoras = diff / ((double) 1000 * 60 * 60);
	    int horasACobrar = (int) diferenciaEnHoras;
	    int minutos = (int) ((diferenciaEnHoras - horasACobrar) * 60);
	    if (minutos > 10 || horasACobrar == 0)
		horasACobrar++;
	    importe = detalleEstadia.getTarifa().getImporte() * horasACobrar;
	    if (detalleEstadia.getPromocion() != null) {
		importe = importe * (1 - detalleEstadia.getPromocion().getDescuento() / 100);
	    }
	} else if ("Por Mes".equals(tipoEstadia.getNombre())) {
	    Calendar calendario = Calendar.getInstance();
	    calendario.clear();
	    calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
	    calendario.add(Calendar.MONTH, 1);
	    fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
	    detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
	    importe = detalleEstadia.getTarifa().getImporte();
	    if (detalleEstadia.getPromocion() != null) {
		importe = importe * (1 - detalleEstadia.getPromocion().getDescuento() / 100);
	    }
	} else if ("Por Noche".equals(tipoEstadia.getNombre())) {
	    Calendar calendario = Calendar.getInstance();
	    calendario.clear();
	    calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
	    calendario.add(Calendar.HOUR_OF_DAY, 8);
	    fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
	    detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
	    importe = detalleEstadia.getTarifa().getImporte();
	    if (detalleEstadia.getPromocion() != null) {
		importe = importe * (1 - detalleEstadia.getPromocion().getDescuento() / 100);
	    }
	} else if ("Por Día".equals(tipoEstadia.getNombre())) {
	    Calendar calendario = Calendar.getInstance();
	    calendario.clear();
	    calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
	    calendario.add(Calendar.DAY_OF_MONTH, 1);
	    fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
	    detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
	    importe = detalleEstadia.getTarifa().getImporte();
	    if (detalleEstadia.getPromocion() != null) {
		importe = importe * (1 - detalleEstadia.getPromocion().getDescuento() / 100);
	    }
	} else if ("Por Semana".equals(tipoEstadia.getNombre())) {
	    Calendar calendario = Calendar.getInstance();
	    calendario.clear();
	    calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
	    calendario.add(Calendar.DAY_OF_MONTH, 7);
	    fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
	    detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
	    importe = detalleEstadia.getTarifa().getImporte();
	    if (detalleEstadia.getPromocion() != null) {
		importe = importe * (1 - detalleEstadia.getPromocion().getDescuento() / 100);
	    }
	}
	detalleEstadia.setImporteTotal(importe);
	detalleEstadia.setCobrado(true);
	importeCalculado = true;
    }

    public String registrarEgresoVehiculo() {
	// actualizar saldo del cliente
	CuentaCliente cuentaCliente = cliente.getCuentaCliente();
	Float saldoActual = cuentaCliente.getSaldo();

	TransaccionCliente transaccionCliente;
	Float saldo;

	try {

	    TipoPago tipoPagoCuenta = getTipoPagoService().findByNombreTipoPago("Cuenta");
	    TipoPago tipoPagoEfectivo = getTipoPagoService().findByNombreTipoPago("Contado");

	    if (saldoActual < importe) {
		detalleEstadia.setImporteTotal(saldoActual);
		// Actualizo la cuenta playon
		saldo = importe - saldoActual;
		cuentaCliente.setSaldo(0);
		getCuentaClienteService().update(cuentaCliente);

		// crear la transacción del cliente con la cuenta playón
		transaccionCliente = new TransaccionCliente(detalleEstadia.getFechaHoraEgreso(), saldoActual,
			tipoPagoCuenta, cuentaCliente);
		getTransaccionClienteService().save(transaccionCliente);

		// actualizar y cerrar detalle estadía
		detalleEstadia.setTransaccionCliente(transaccionCliente);
		getDetalleEstadiaService().update(detalleEstadia);

		// crear la transacción del cliente con pago efectivo
		transaccionCliente = new TransaccionCliente(detalleEstadia.getFechaHoraEgreso(), saldo,
			tipoPagoEfectivo, cuentaCliente);
		getTransaccionClienteService().save(transaccionCliente);
		
		TransaccionPlaya txPlaya;
		
		// Registrar la transacción playa con cuenta playon.
		    txPlaya = new TransaccionPlaya();
		    txPlaya.setFecha(detalleEstadia.getFechaHoraEgreso());
		    txPlaya.setDetalleEstadia(detalleEstadia);
		    txPlaya.setCuentaPlaya(cuentaPlaya);
		    txPlaya.setImporte(saldoActual);
		    txPlaya.setTipoPago(tipoPagoCuenta);
		    getTransaccionPlayaService().save(txPlaya);
		    
		    DetalleEstadia detalleEstadiaEfectivo = detalleEstadia;
		    detalleEstadiaEfectivo.setImporteTotal(saldo);
		    
		 // Registrar la transacción playa con efectivo.
		    txPlaya = new TransaccionPlaya();
		    txPlaya.setFecha(detalleEstadiaEfectivo.getFechaHoraEgreso());
		    txPlaya.setDetalleEstadia(detalleEstadiaEfectivo);
		    txPlaya.setCuentaPlaya(cuentaPlaya);
		    txPlaya.setImporte(saldo);
		    txPlaya.setTipoPago(tipoPagoEfectivo);
		    getTransaccionPlayaService().save(txPlaya);
		    
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			    "Debe cobrar en efectivo $" + saldo, null);
		    FacesContext.getCurrentInstance().addMessage(null, message);

	    } else {
		// actualizar saldo de la playa
		float saldoActualizado = cuentaCliente.getSaldo() - importe;
		cuentaCliente.setSaldo(saldoActualizado);
		getCuentaClienteService().update(cuentaCliente);

		// crear la transaccion del cliente con la cuenta playon
		transaccionCliente = new TransaccionCliente(detalleEstadia.getFechaHoraEgreso(), -importe,
			tipoPagoCuenta, cuentaCliente);
		getTransaccionClienteService().save(transaccionCliente);

		// actualizar y cerrar detalle estadia
		detalleEstadia.setTransaccionCliente(transaccionCliente);
		getDetalleEstadiaService().update(detalleEstadia);
		
		// Registrar la transacción playa.
		    TransaccionPlaya txPlaya = new TransaccionPlaya();
		    txPlaya.setFecha(detalleEstadia.getFechaHoraIngreso());
		    txPlaya.setDetalleEstadia(detalleEstadia);
		    txPlaya.setCuentaPlaya(cuentaPlaya);
		    txPlaya.setImporte(importe);
		    txPlaya.setTipoPago(tipoPagoCuenta);
		    getTransaccionPlayaService().save(txPlaya);
	    }

	    // actualizar lugares en una playa
	    Integer disponibilidad = playaLoggeada.getDisponibilidad() + 1;
	    playaLoggeada.setDisponibilidad(disponibilidad);
	    getPlayaService().update(playaLoggeada);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró el egreso exitosamente del vehículo patente: "+ patente, null);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    
	    return "/playa/ingresoegresovehiculo";
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void handleTarifa() {
	if (tarifaSeleccionada != null && !tarifaSeleccionada.equals("")) {
	    TipoEstadia tipo = getTipoEstadiaService().findByNombreTipoEstadia(
		    tarifaSeleccionada.getTipoEstadia().getNombre());
	    promocionesDisponibles = getPromocionService().findByTipoEstadiaAndPlaya(tipo, playaLoggeada);
	} else
	    promocionesDisponibles = new ArrayList<Promocion>();
    }

    // =================================== SERVICES =========================================

    public IEmpleadoService getEmpleadoService() {
	return empleadoService;
    }

    public void setEmpleadoService(IEmpleadoService empleadoService) {
	this.empleadoService = empleadoService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public ICargoEmpleadoService getCargoEmpleadoService() {
	return cargoEmpleadoService;
    }

    public void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
	this.cargoEmpleadoService = cargoEmpleadoService;
    }

    public ICuentaClienteService getCuentaClienteService() {
	return cuentaClienteService;
    }

    public void setCuentaClienteService(ICuentaClienteService cuentaClienteService) {
	this.cuentaClienteService = cuentaClienteService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public ICuentaPlayaService getCuentaPlayaService() {
	return cuentaPlayaService;
    }

    public void setCuentaPlayaService(ICuentaPlayaService cuentaPlayaService) {
	this.cuentaPlayaService = cuentaPlayaService;
    }

    public IVehiculoService getVehiculoService() {
	return vehiculoService;
    }

    public void setVehiculoService(IVehiculoService vehiculoService) {
	this.vehiculoService = vehiculoService;
    }

    public IEstadiaService getEstadiaService() {
	return estadiaService;
    }

    public void setEstadiaService(IEstadiaService estadiaService) {
	this.estadiaService = estadiaService;
    }

    public IDetalleEstadiaService getDetalleEstadiaService() {
	return detalleEstadiaService;
    }

    public void setDetalleEstadiaService(IDetalleEstadiaService detalleEstadiaService) {
	this.detalleEstadiaService = detalleEstadiaService;
    }

    public ITarifaService getTarifaService() {
	return tarifaService;
    }

    public void setTarifaService(ITarifaService tarifaService) {
	this.tarifaService = tarifaService;
    }

    public IPromocionService getPromocionService() {
	return promocionService;
    }

    public void setPromocionService(IPromocionService promocionService) {
	this.promocionService = promocionService;
    }

    public ITipoPagoService getTipoPagoService() {
	return tipoPagoService;
    }

    public void setTipoPagoService(ITipoPagoService tipoPagoService) {
	this.tipoPagoService = tipoPagoService;
    }

    public ITransaccionClienteService getTransaccionClienteService() {
	return transaccionClienteService;
    }

    public void setTransaccionClienteService(ITransaccionClienteService transaccionClienteService) {
	this.transaccionClienteService = transaccionClienteService;
    }

    public ITransaccionPlayaService getTransaccionPlayaService() {
	return transaccionPlayaService;
    }

    public void setTransaccionPlayaService(ITransaccionPlayaService transaccionPlayaService) {
	this.transaccionPlayaService = transaccionPlayaService;
    }

    public ITipoEstadiaService getTipoEstadiaService() {
	return tipoEstadiaService;
    }

    public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
	this.tipoEstadiaService = tipoEstadiaService;
    }

    // =============================== GETTER & SETTER =====================================

    public IAbonoService getAbonoService() {
        return abonoService;
    }

    public void setAbonoService(IAbonoService abonoService) {
        this.abonoService = abonoService;
    }

    public Usuario getUsuarioLoggeado() {
	return usuarioLoggeado;
    }

    public List<TipoEstadia> getTipoEstadiaList() {
	return tipoEstadiaList;
    }

    public void setTipoEstadiaList(List<TipoEstadia> tipoEstadiaList) {
	this.tipoEstadiaList = tipoEstadiaList;
    }

    public List<Tarifa> getTarifaPlayaList() {
	return tarifaPlayaList;
    }

    public void setTarifaPlayaList(List<Tarifa> tarifaPlayaList) {
	this.tarifaPlayaList = tarifaPlayaList;
    }

    public List<Promocion> getPromocionesDisponibles() {
	return promocionesDisponibles;
    }

    public void setPromocionesDisponibles(List<Promocion> promocionesDisponibles) {
	this.promocionesDisponibles = promocionesDisponibles;
    }

    public void setUsuarioLoggeado(Usuario usuarioLoggeado) {
	this.usuarioLoggeado = usuarioLoggeado;
    }

    public Usuario getUsuarioCliente() {
	return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
	this.usuarioCliente = usuarioCliente;
    }

    public Empleado getEmpleadoLoggeado() {
	return empleadoLoggeado;
    }

    public void setEmpleadoLoggeado(Empleado empleadoLoggeado) {
	this.empleadoLoggeado = empleadoLoggeado;
    }

    public Playa getPlayaLoggeada() {
	return playaLoggeada;
    }

    public void setPlayaLoggeada(Playa playaLoggeada) {
	this.playaLoggeada = playaLoggeada;
    }

    public CuentaPlaya getCuentaPlaya() {
	return cuentaPlaya;
    }

    public void setCuentaPlaya(CuentaPlaya cuentaPlaya) {
	this.cuentaPlaya = cuentaPlaya;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public CargoEmpleado getCargoEmpleado() {
	return cargoEmpleado;
    }

    public void setCargoEmpleado(CargoEmpleado cargoEmpleado) {
	this.cargoEmpleado = cargoEmpleado;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public DetalleEstadia getDetalleEstadia() {
	return detalleEstadia;
    }

    public void setDetalleEstadia(DetalleEstadia detalleEstadia) {
	this.detalleEstadia = detalleEstadia;
    }

    public Date getFechaIngreso() {
	return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
	this.fechaIngreso = fechaIngreso;
    }

    public Time getHoraIngreso() {
	return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
	this.horaIngreso = horaIngreso;
    }

    public Date getFechaActual() {
	fechaActual = new Date();
	return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
	this.fechaActual = fechaActual;
    }

    public String getHoraActual() {
	String horaActual = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	return horaActual;
    }

    public void setHoraActual(String horaActual) {
	this.horaActual = horaActual;
    }

    public Float getImporte() {
	return importe;
    }

    public void setImporte(Float importe) {
	this.importe = importe;
    }

    // ======================= GETTER & SETTER ADICIONALES =====================

    public Tarifa getTarifaSeleccionada() {
	return tarifaSeleccionada;
    }

    public void setTarifaSeleccionada(Tarifa tarifaSeleccionada) {
	this.tarifaSeleccionada = tarifaSeleccionada;
    }

    public Promocion getPromocionSeleccionada() {
	return promocionSeleccionada;
    }

    public void setPromocionSeleccionada(Promocion promocionSeleccionada) {
	this.promocionSeleccionada = promocionSeleccionada;
    }

    public boolean isSaldoPositivo() {
	return saldoPositivo;
    }

    public void setSaldoPositivo(boolean saldoPositivo) {
	this.saldoPositivo = saldoPositivo;
    }

    public boolean isExisteTarifa() {
	return existeTarifa;
    }

    public void setExisteTarifa(boolean existeTarifa) {
	this.existeTarifa = existeTarifa;
    }

    public boolean isExisteVehiculo() {
	return existeVehiculo;
    }

    public void setExisteVehiculo(boolean existeVehiculo) {
	this.existeVehiculo = existeVehiculo;
    }

    public boolean isImprimir() {
	return imprimir;
    }

    public void setImprimir(boolean imprimir) {
	this.imprimir = imprimir;
    }

    public boolean isImporteCalculado() {
	return importeCalculado;
    }

    public void setImporteCalculado(boolean importeCalculado) {
	this.importeCalculado = importeCalculado;
    }

    public boolean isExisteAbonoVehiculo() {
        return existeAbonoVehiculo;
    }

    public void setExisteAbonoVehiculo(boolean existeAbonoVehiculo) {
        this.existeAbonoVehiculo = existeAbonoVehiculo;
    }

}
