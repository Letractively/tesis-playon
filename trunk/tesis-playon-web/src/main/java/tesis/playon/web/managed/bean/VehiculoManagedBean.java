/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.ColorVehiculo;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IColorVehiculoService;
import tesis.playon.web.service.IMarcaVehiculoService;
import tesis.playon.web.service.IModeloVehiculoService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "vehiculoMB")
@ViewScoped
public class VehiculoManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    @ManagedProperty(value = "#{VehiculoService}")
    IVehiculoService vehiculoService;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{ModeloVehiculoService}")
    IModeloVehiculoService modeloVehiculoService;

    @ManagedProperty(value = "#{MarcaVehiculoService}")
    IMarcaVehiculoService marcaVehiculoService;

    @ManagedProperty(value = "#{ColorVehiculoService}")
    IColorVehiculoService colorVehiculoService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    private int anio;

    // private static final String LISTA_VEHICULOS = "vehiculolist";

    private static final String ERROR = "error";

    private static Vehiculo vehiculoSelected;

    private CategoriaVehiculo categoriaVehiculo;

    private String codigoBarra;

    private List<Vehiculo> vehiculoList;

    private ColorVehiculo colorVehiculo;

    private boolean habilitado;

    private ModeloVehiculo modeloVehiculo;

    private String patente;

    private Cliente cliente;

    // ATRIBUTOS ADICIONALES DEL VEHICULO
    private MarcaVehiculo marca;

    private List<MarcaVehiculo> marcasList;

    private List<ModeloVehiculo> modelosList;

    private List<ColorVehiculo> colorVehiculoList;
    
    private List<CategoriaVehiculo> categoriaVehiculoList;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	this.cliente = getClienteService().findByNombreUsuario(userName);
	marcasList = getMarcaVehiculoService().findAll();
	colorVehiculoList = getColorVehiculoService().findAll();
    }

    public String addVehiculo() {

	Vehiculo vehiculo = new Vehiculo();
	try {
	    if (cliente != null) {
		vehiculo.setAnio(getAnio());
		vehiculo.setCliente(cliente);
		vehiculo.setCodigoBarra(getCodigoBarra());
		vehiculo.setColorVehiculo(getColorVehiculo());
		vehiculo.setModeloVehiculo(getModeloVehiculo());
		vehiculo.setPatente(getPatente());
		vehiculo.setHabilitado(true);

		getVehiculoService().save(vehiculo);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Se agregó correctamente el vehículo con patente: " + vehiculo.getPatente(), "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	    return "vehiculoaddend";

	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el vehículo con patente: " + vehiculo.getPatente(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return "/error";
    }

    public void handleMarcaChange() {
	if (marca != null && !marca.equals(""))
	    modelosList = getModeloVehiculoService().findByMarca(marca);
	else
	    modelosList = new ArrayList<ModeloVehiculo>();
    }

    public IVehiculoService getVehiculoService() {
	return vehiculoService;
    }

    public void setVehiculoService(IVehiculoService vehiculoService) {
	this.vehiculoService = vehiculoService;
    }

    public IClienteService getClienteService() {
	return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

    public IModeloVehiculoService getModeloVehiculoService() {
	return modeloVehiculoService;
    }

    public void setModeloVehiculoService(IModeloVehiculoService modeloVehiculoService) {
	this.modeloVehiculoService = modeloVehiculoService;
    }

    public IMarcaVehiculoService getMarcaVehiculoService() {
	return marcaVehiculoService;
    }

    public void setMarcaVehiculoService(IMarcaVehiculoService marcaVehiculoService) {
	this.marcaVehiculoService = marcaVehiculoService;
    }

    public IColorVehiculoService getColorVehiculoService() {
	return colorVehiculoService;
    }

    public void setColorVehiculoService(IColorVehiculoService colorVehiculoService) {
	this.colorVehiculoService = colorVehiculoService;
    }

    public List<MarcaVehiculo> getMarcasList() {
	return marcasList;
    }

    public void setMarcasList(List<MarcaVehiculo> marcasList) {
	this.marcasList = marcasList;
    }

    public List<ModeloVehiculo> getModelosList() {
	return modelosList;
    }

    public void setModelosList(List<ModeloVehiculo> modelosList) {
	this.modelosList = modelosList;
    }

    public List<ColorVehiculo> getColorVehiculoList() {
	return colorVehiculoList;
    }

    public void setColorVehiculoList(List<ColorVehiculo> colorVehiculoList) {
	this.colorVehiculoList = colorVehiculoList;
    }

    public int getAnio() {
	return anio;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public CategoriaVehiculo getCategoriaVehiculo() {
	return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
	this.categoriaVehiculo = categoriaVehiculo;
    }

    public String getCodigoBarra() {
	return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
	this.codigoBarra = codigoBarra;
    }

    public ColorVehiculo getColorVehiculo() {
	return colorVehiculo;
    }

    public void setColorVehiculo(ColorVehiculo colorVehiculo) {
	this.colorVehiculo = colorVehiculo;
    }

    public boolean isHabilitado() {
	return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
	this.habilitado = habilitado;
    }

    public ModeloVehiculo getModeloVehiculo() {
	return modeloVehiculo;
    }

    public void setModeloVehiculo(ModeloVehiculo modeloVehiculo) {
	this.modeloVehiculo = modeloVehiculo;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public MarcaVehiculo getMarca() {
	return marca;
    }

    public void setMarca(MarcaVehiculo marca) {
	this.marca = marca;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public String deleteVehiculo() {
	try {
	    vehiculoSelected.setHabilitado(new Boolean(false));

	    getVehiculoService().update(vehiculoSelected);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja el vehiculo: "
		    + vehiculoSelected.getPatente() + " " + "", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    // reset();
	    return "/cliente/vehiculolist";
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja el empleado: " + vehiculoSelected.getPatente() + " " + " ",
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    // reset();
	}
	return ERROR;
    }

    public List<Vehiculo> getVehiculoList() {
	vehiculoList = new ArrayList<Vehiculo>();
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario usuario = getUsuarioService().findByNombreUsuario(userName);
	cliente = getClienteService().findByUsuario(usuario);

	vehiculoList = getVehiculoService().findByCliente(cliente.getId());

	return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
	this.vehiculoList = vehiculoList;
    }

    public Vehiculo getVehiculoSelected() {
	return vehiculoSelected;
    }

    public void setVehiculoSelected(Vehiculo vehiculoSelected) {
	VehiculoManagedBean.vehiculoSelected = vehiculoSelected;
    }

    public List<CategoriaVehiculo> getCategoriaVehiculoList() {
        return categoriaVehiculoList;
    }

    public void setCategoriaVehiculoList(List<CategoriaVehiculo> categoriaVehiculoList) {
        this.categoriaVehiculoList = categoriaVehiculoList;
    }
}