package tesis.playon.web.managed.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoPago;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.ICuentaPlayaService;
import tesis.playon.web.service.IDetalleEstadiaService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IEstadiaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoPagoService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

@ManagedBean(name = "estadisticaGerenteMB")
@ViewScoped
public class EstadisticaGerenteManagedBean {
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

    @ManagedProperty(value = "#{TipoPagoService}")
    ITipoPagoService tipoPagoService;

    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    private List<Tarifa> tarifaPlayaList;

    private Usuario usuario;

    private Usuario usuarioCliente;

    private Empleado empleado;

    private CargoEmpleado cargoEmpleado;

    private Playa playa;

    private CuentaPlaya cuentaPlaya;

    private DetalleEstadia detalleEstadia;

    private Tarifa tarifa;

    private Cliente cliente;

    private CuentaCliente cuentaCliente;

    private TransaccionCliente transaccionCliente;

    private TipoPago tipoPago;

    private Vehiculo vehiculo;

    private CategoriaVehiculo categoriaVehiculo;

    private ModeloVehiculo modeloVehiculo;

    private MarcaVehiculo marcaVehiculo;

    private String nombreUsuario;

    private String patente;

    private String fechaIngresoFormateada;

    private String horaIngresoFormateada;

    private boolean existeVehiculo = false;

    private boolean existeTarifa = true;

    private boolean saldoPositvo = false;

    private boolean cobrado = true;

    private boolean importeCalculado;

    private static Playa playaSelected;

    private static Estadia estadia;

    private static List<DetalleEstadia> detalles;

    private static Usuario user;

    private float importe;

    private static int cantAutos, cantUtilitarios, cantMotos, cantPickUp;
    private int mayorCant;

    @SuppressWarnings("unused")
    private static double ingAuto, ingUtilitario, ingMoto, ingPickUp;

    @PostConstruct
    public void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario usuario = getUsuarioService().findByNombreUsuario(userName);
	playaSelected = usuario.getPlaya();
	setEstadia(getEstadiaService().findByPlaya(playaSelected));
	setDetalles(getDetalleEstadiaService().findByEstadia(estadia));
	cantidadTiposVehiculo();
	// importePorTipo();
	createLinearModel();
	createCategoryModel();

    }

    private void cantidadTiposVehiculo() {
	cantAutos = 13;
	cantMotos = 100;
	cantPickUp = 50;
	cantUtilitarios = 5;

	for (DetalleEstadia detalleAux : detalles) {
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Auto")) {
		cantAutos++;
	    }
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Moto")) {
		cantMotos++;
	    }
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Utilitario")) {
		cantUtilitarios++;
	    }
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Pickup")) {
		cantPickUp++;
	    }

	}
	maximoValor();

    }

    private void maximoValor() {
	mayorCant = cantAutos;

	if (cantMotos > mayorCant) {
	    mayorCant = cantMotos;
	    if (cantUtilitarios > mayorCant)
		mayorCant = cantUtilitarios;
	    if (cantPickUp > mayorCant)
		mayorCant = cantPickUp;
	}

	else if (cantUtilitarios > mayorCant) {
	    mayorCant = cantUtilitarios;
	    if (cantPickUp > mayorCant)
		mayorCant = cantPickUp;

	}

	else if (cantPickUp > mayorCant)
	    mayorCant = cantPickUp;

	mayorCant = mayorCant + (int) (mayorCant * 0.25);
    }

    private void importePorTipo()

    {
	ingAuto = 0;
	ingMoto = 0;
	ingUtilitario = 0;
	ingPickUp = 0;
	for (DetalleEstadia detalleAux : detalles) {
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Auto")) {
		ingAuto += detalleAux.getImporteTotal();
	    }
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Moto")) {
		ingMoto += detalleAux.getImporteTotal();

	    }
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Utilitario")) {
		ingUtilitario += detalleAux.getImporteTotal();
	    }
	    if (detalleAux.getVehiculo().getModeloVehiculo().getCategoriaVehiculo().getNombre().equals("Pickup")) {
		ingPickUp += detalleAux.getImporteTotal();
	    }

	}
    }

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

    public List<Tarifa> getTarifaPlayaList() {
	tarifaPlayaList = new ArrayList<Tarifa>();
	if (null != getTarifaService().findTarifaVigenteByPlayaAndCategoriaVehiculo(playa, categoriaVehiculo)) {
	    tarifaPlayaList.addAll(getTarifaService().findTarifaVigenteByPlayaAndCategoriaVehiculo(playa,
		    categoriaVehiculo));
	}
	return tarifaPlayaList;
    }

    public void setTarifaPlayaList(List<Tarifa> tarifaPlayaList) {
	this.tarifaPlayaList = tarifaPlayaList;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Usuario getUsuarioCliente() {
	return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
	this.usuarioCliente = usuarioCliente;
    }

    public Empleado getEmpleado() {
	return empleado;
    }

    public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
    }

    public CargoEmpleado getCargoEmpleado() {
	return cargoEmpleado;
    }

    public void setCargoEmpleado(CargoEmpleado cargoEmpleado) {
	this.cargoEmpleado = cargoEmpleado;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public CuentaPlaya getCuentaPlaya() {
	return cuentaPlaya;
    }

    public void setCuentaPlaya(CuentaPlaya cuentaPlaya) {
	this.cuentaPlaya = cuentaPlaya;
    }

    public DetalleEstadia getDetalleEstadia() {
	return detalleEstadia;
    }

    public void setDetalleEstadia(DetalleEstadia detalleEstadia) {
	this.detalleEstadia = detalleEstadia;
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

    public CuentaCliente getCuentaCliente() {
	return cuentaCliente;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
	this.cuentaCliente = cuentaCliente;
    }

    public TransaccionCliente getTransaccionCliente() {
	return transaccionCliente;
    }

    public void setTransaccionCliente(TransaccionCliente transaccionCliente) {
	this.transaccionCliente = transaccionCliente;
    }

    public TipoPago getTipoPago() {
	return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
	this.tipoPago = tipoPago;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public CategoriaVehiculo getCategoriaVehiculo() {
	return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
	this.categoriaVehiculo = categoriaVehiculo;
    }

    public ModeloVehiculo getModeloVehiculo() {
	return modeloVehiculo;
    }

    public void setModeloVehiculo(ModeloVehiculo modeloVehiculo) {
	this.modeloVehiculo = modeloVehiculo;
    }

    public MarcaVehiculo getMarcaVehiculo() {
	return marcaVehiculo;
    }

    public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
	this.marcaVehiculo = marcaVehiculo;
    }

    public String getNombreUsuario() {
	return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public String getFechaIngresoFormateada() {
	return fechaIngresoFormateada;
    }

    public void setFechaIngresoFormateada(String fechaIngresoFormateada) {
	this.fechaIngresoFormateada = fechaIngresoFormateada;
    }

    public String getFechaActualFormateada() {

	return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

    public String getHoraIngresoFormateada() {
	return horaIngresoFormateada;
    }

    public void setHoraIngresoFormateada(String horaIngresoFormateada) {
	this.horaIngresoFormateada = horaIngresoFormateada;
    }

    public String getHoraActualFormateada() {
	return new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
    }

    public boolean getExisteVehiculo() {
	return existeVehiculo;
    }

    public void setExisteVehiculo(boolean existeVehiculo) {
	this.existeVehiculo = existeVehiculo;
    }

    public boolean getExisteTarifa() {
	return existeTarifa;
    }

    public void setExisteTarifa(boolean existeTarifa) {
	this.existeTarifa = existeTarifa;
    }

    public boolean getSaldoPositvo() {
	return saldoPositvo;
    }

    public void setSaldoPositvo(boolean saldoPositvo) {
	this.saldoPositvo = saldoPositvo;
    }

    public boolean getCobrado() {
	return cobrado;
    }

    public void setCobrado(boolean cobrado) {
	this.cobrado = cobrado;
    }

    public boolean getImporteCalculado() {
	return importeCalculado;
    }

    public void setImporteCalculado(boolean importeCalculado) {
	this.importeCalculado = importeCalculado;
    }

    public float getImporte() {
	return importe;
    }

    public void setImporte(float importe) {
	this.importe = importe;
    }

    public static Playa getPlayaSelected() {
	return playaSelected;
    }

    public static void setPlayaSelected(Playa playaSelected) {
	EstadisticaGerenteManagedBean.playaSelected = playaSelected;
    }

    public static Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public static List<DetalleEstadia> getDetalles() {
	return detalles;
    }

    public static void setDetalles(List<DetalleEstadia> detalles) {
	EstadisticaGerenteManagedBean.detalles = detalles;
    }

    public static Usuario getUser() {
	return user;
    }

    public static void setUser(Usuario user) {
	EstadisticaGerenteManagedBean.user = user;
    }

    public int getMayorCant() {
	return mayorCant;
    }

    public void setMayorCant(int mayorCant) {
	this.mayorCant = mayorCant;
    }

    /**************************************************************************************************/
    private static CartesianChartModel linearModel;

    public CartesianChartModel getLinearModel() {
	return linearModel;
    }

    public void setLinearModel(CartesianChartModel linearModel) {
	EstadisticaGerenteManagedBean.linearModel = linearModel;
    }

    public CartesianChartModel getCategoryModel() {
	return categoryModel;
    }

    public void setCategoryModel(CartesianChartModel categoryModel) {
	this.categoryModel = categoryModel;
    }

    private CartesianChartModel categoryModel;

    private void createLinearModel() {
	linearModel = new CartesianChartModel();

	LineChartSeries series1 = new LineChartSeries();
	series1.setLabel("Series 1");

	series1.set(1, 2);
	series1.set(2, 1);
	series1.set(3, 3);
	series1.set(4, 6);
	series1.set(5, 8);

	LineChartSeries series2 = new LineChartSeries();
	series2.setLabel("Series 2");
	series2.setMarkerStyle("diamond");
	series2.set(1, 6);
	series2.set(2, 3);
	series2.set(3, 2);
	series2.set(4, 7);
	series2.set(5, 9);

	linearModel.addSeries(series1);
	linearModel.addSeries(series2);
    }

    private void createCategoryModel() {
	categoryModel = new CartesianChartModel();

	ChartSeries autos = new ChartSeries();
	ChartSeries motos = new ChartSeries();
	ChartSeries utilitarios = new ChartSeries();
	ChartSeries pickup = new ChartSeries();
	ChartSeries blanco = new ChartSeries();

	autos.setLabel("Autos");
	motos.setLabel("Motos");
	utilitarios.setLabel("Utilitarios");
	pickup.setLabel("Pick-Up");

	autos.set("Autos", cantAutos);
	motos.set("Motos", cantMotos);
	utilitarios.set("Utilitarios", cantUtilitarios);
	pickup.set("PickUP", cantPickUp);
	categoryModel.addSeries(autos);
	categoryModel.addSeries(motos);
	categoryModel.addSeries(utilitarios);
	categoryModel.addSeries(pickup);
	categoryModel.addSeries(blanco);
    }

}
