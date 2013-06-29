/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

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

    private String rutaPDF = "resources" + File.separator + "archivo.pdf";

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
			cuentaPlaya.setNroCuenta(playaLoggeada.getId() + 1000);
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
		existeAbonoVehiculo = getAbonoService().existeAbonoVehiculo(vehiculo, playaLoggeada);

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

		// promocionesDisponibles =
		// getPromocionService().findByCategoria(
		// vehiculo.getModeloVehiculo().getCategoriaVehiculo(),
		// playaLoggeada);

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
			"No se encontró el vehículo con patente: " + auxPatente, "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		setExisteVehiculo(false);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String registrarIngresoAbono() {
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
		    "Se registró el ingreso exitosamente del vehiculo con abono mensual con patente: " + patente, "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    existeAbonoVehiculo = false;

	    return "/playa/ingresoegresovehiculo";
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String registrarEgresoAbonado() {
	try {
	    Timestamp fechaHoraEgreso = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
	    detalleEstadia.setCobrado(true);
	    getDetalleEstadiaService().update(detalleEstadia);

	    Integer disponibilidad = playaLoggeada.getDisponibilidad() + 1;
	    playaLoggeada.setDisponibilidad(disponibilidad);
	    getPlayaService().update(playaLoggeada);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró el egreso exitosamente del vehículo con abono patente: " + patente, "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    existeAbonoVehiculo = false;

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
		    "Se registró el ingreso exitosamente del vehiculo patente: " + patente, "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    this.generarTicketIngreso();

	    return "/playa/ingresoegresovehiculo?faces-redirect=true";
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String redirect() {
	return "/playa/ingresoegresovehiculo?faces-redirect=true";
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

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Debe cobrar en efectivo $"
			+ saldo, "");
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
		    "Se registró el egreso exitosamente del vehículo patente: " + patente, "");
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

    // =================================== SERVICES
    // =========================================

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

    // =============================== GETTER & SETTER
    // =====================================

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

    // -------------------- DESCARGAR PDF
    // private StreamedContent file;

    // public StreamedContent getFile() {
    // try {
    //
    // Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
    //
    // File filePDF = new File("/tmp/pdf.pdf");
    // OutputStream os = new FileOutputStream(filePDF);
    //
    // @SuppressWarnings("unused")
    // PdfWriter writer = PdfWriter.getInstance(doc, os);
    //
    // // ******definicion de letras y parrafos********************
    // Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.BLACK);
    // Paragraph titulo = new Paragraph();
    // titulo.add(new Paragraph("Comprobante de ingreso emitido el: " + fechaActual(), fuenteNegra18));
    //
    // Font fuenteCuerpo = new Font(Font.TIMES_ROMAN, 14, Font.NORMAL, Color.BLACK);
    //
    // // Paragraph cuerpo = new Paragraph();
    // agregarLineasEnBlanco(titulo, 2);
    // titulo.add(new Paragraph("Hora de ingreso: " + getDetalleEstadia().getFechaHoraIngreso(), fuenteCuerpo));
    //
    // titulo.add(new Paragraph("Hora de ingreso: "
    // + "una vez que el archivo descargue, poner fecha de egreso aca!", fuenteCuerpo));
    // titulo.add(new Paragraph("Patente: " + getDetalleEstadia().getVehiculo().getPatente(), fuenteCuerpo));
    // titulo.add(new Paragraph("Modelo: " + getDetalleEstadia().getVehiculo().getModeloVehiculo().getNombre(),
    // fuenteCuerpo));
    // titulo.add(new Paragraph("Importe: " + getDetalleEstadia().getImporteTotal(), fuenteCuerpo));
    // titulo.add(new Paragraph("Saldo: "
    // + getDetalleEstadia().getVehiculo().getCliente().getCuentaCliente().getSaldo(), fuenteCuerpo));
    //
    // String sep = File.separator;
    // ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
    // String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
    // /************************************/
    // doc.open();
    // doc.addTitle("Listado de empleados");
    // doc.add(Image.getInstance(logo));
    // doc.add(titulo);
    // doc.close();
    // /**************************************/
    // InputStream stream = new FileInputStream(filePDF);
    //
    // file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_primefaces.pdf");
    // return file;
    // } catch (Exception e) {
    //
    // }
    // return null;
    // }

    // -------------------- DESCARGAR PDF
     private StreamedContent fileIngreso;

    public StreamedContent getFileIngreso() {
	try {

	    Document doc = new Document(PageSize.A6, 10, 10, 10, 10);

	    File filePDF = new File("/tmp/pdf.pdf");
	    OutputStream os = new FileOutputStream(filePDF);

	    @SuppressWarnings("unused")
	    PdfWriter writer = PdfWriter.getInstance(doc, os);

	    // ******definicion de letras y parrafos********************
	    Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.BLACK);
	    Paragraph cuerpo = new Paragraph();
	    cuerpo.add(new Paragraph("Comprobante de ingreso emitido el: " + fechaActual(), fuenteNegra18));

	    Font fuenteCuerpo = new Font(Font.TIMES_ROMAN, 14, Font.NORMAL, Color.BLACK);

	    // Paragraph cuerpo = new Paragraph();
	    agregarLineasEnBlanco(cuerpo, 2);
	    // titulo.add(new Paragraph("prueba"));
	    cuerpo.add(new Paragraph("Hora de ingreso: " + new Timestamp(Calendar.getInstance().getTimeInMillis()),
		    fuenteCuerpo));
	    cuerpo.add(new Paragraph("Patente: " + this.getVehiculo().getPatente(), fuenteCuerpo));
	    cuerpo.add(new Paragraph("Modelo: " + this.getVehiculo().getModeloVehiculo().getNombre(), fuenteCuerpo));
	    cuerpo.add(new Paragraph("Saldo: " + this.getVehiculo().getCliente().getCuentaCliente().getSaldo(),
		    fuenteCuerpo));

	    String sep = File.separator;
	    ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	    String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	    /************************************/
	    doc.open();
	    doc.addTitle("Listado de empleados");
	    doc.add(Image.getInstance(logo));
	    doc.add(cuerpo);
	    doc.close();
	    /**************************************/
	    InputStream stream = new FileInputStream(filePDF);

	    fileIngreso = new DefaultStreamedContent(stream, "application/pdf", "downloaded_primefaces.pdf");
	    return fileIngreso;
	} catch (Exception e) {

	}
	return null;
    }
    
    // private StreamedContent ticketIngreso;

    private static void close(Closeable resource) {
	if (resource != null) {
	    try {
		resource.close();
	    } catch (IOException e) {
		// Do your thing with the exception. Print it, log it or mail it. It may be useful to
		// know that this will generally only be thrown when the client aborted the download.
		e.printStackTrace();
	    }
	}
    }

    /**
     * @return the ticket Ingreso File
     */
    public void generarTicketIngreso() throws IOException {
	try {

	    Document doc = new Document(PageSize.A6, 10, 10, 10, 10);

	    File filePDF = new File("/tmp/pdf.pdf");
	    OutputStream os = new FileOutputStream(filePDF);

	    @SuppressWarnings("unused")
	    PdfWriter writer = PdfWriter.getInstance(doc, os);

	    // ******definicion de letras y parrafos********************
	    Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.BLACK);
	    Paragraph cuerpo = new Paragraph();
	    cuerpo.add(new Paragraph("Comprobante de ingreso emitido el: " + fechaActual(), fuenteNegra18));

	    Font fuenteCuerpo = new Font(Font.TIMES_ROMAN, 14, Font.NORMAL, Color.BLACK);

	    // Paragraph cuerpo = new Paragraph();
	    agregarLineasEnBlanco(cuerpo, 2);
	    // titulo.add(new Paragraph("prueba"));
	    cuerpo.add(new Paragraph("Hora de ingreso: " + new Timestamp(Calendar.getInstance().getTimeInMillis()),
		    fuenteCuerpo));
	    cuerpo.add(new Paragraph("Patente: " + this.getVehiculo().getPatente(), fuenteCuerpo));
	    cuerpo.add(new Paragraph("Modelo: " + this.getVehiculo().getModeloVehiculo().getNombre(), fuenteCuerpo));
	    cuerpo.add(new Paragraph("Saldo: " + this.getVehiculo().getCliente().getCuentaCliente().getSaldo(),
		    fuenteCuerpo));

	    String sep = File.separator;
	    ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	    String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	    /************************************/
	    doc.open();
	    doc.addTitle("Listado de empleados");
	    doc.add(Image.getInstance(logo));
	    doc.add(cuerpo);
	    doc.close();
	    /**************************************/

	    int DEFAULT_BUFFER_SIZE = 10240;

	    // Prepare.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	    String path = externalContext.getRealPath("resources" + sep + "archivo.pdf");

	    File file = filePDF;
	    BufferedInputStream input = null;
	    BufferedOutputStream output = null;

	    try {
		// Open file.
		input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

		// Init servlet response.
		response.reset();
		response.setHeader("Content-Type", "application/pdf");
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "filename=\"archivo.pdf\"");
		output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

		
		// Write file contents to response.
		//ServletOutputStream sos = response.getOutputStream();

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int length;
		while ((length = input.read(buffer)) > 0) {
		    output.write(buffer, 0, length);
		}

		//sos.flush();
		//sos.close();
		// Finalize task.
		output.flush();
		//facesContext.responseComplete();
	    } finally {
		// Gently close streams.
		close(output);
		close(input);
	    }

	    // Inform JSF that it doesn't need to handle response.
	    // This is very important, otherwise you will get the following exception in the logs:
	    // java.lang.IllegalStateException: Cannot forward after response has been committed.
	    //facesContext.responseComplete();
	    this.setRutaPDF(path);

	} catch (Exception e) {

	}
    }
    
    private String fechaActual() {
	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = formato.format(hoy);
	return fecha;

    }

    private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
	for (int i = 0; i < nLineas; i++)
	    parrafo.add(new Paragraph(" "));
    }

    /**
     * @param file
     *            the file to set
     */
    // public void setFile(StreamedContent file) {
    // this.file = file;
    // }

    public String getRutaPDF() {
	return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
	this.rutaPDF = rutaPDF;
    }

}
