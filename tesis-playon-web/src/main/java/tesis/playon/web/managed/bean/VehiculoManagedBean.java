/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.ColorVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IClienteService;
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

    private int anio;

    private CategoriaVehiculo categoriaVehiculo;

    private String codigoBarra;

    private ColorVehiculo colorVehiculo;

    private ColorVehiculo colorVehiculoNulo;

    private boolean habilitado;

    private ModeloVehiculo modeloVehiculo;

    private String patente;

    private Cliente cliente;

    public String addVehiculo() {

	Vehiculo vehiculo = new Vehiculo();

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String nameUser = auth.getName(); // get logged in username

	Cliente cliente = getClienteService().findByNombreUsuario(nameUser);

	try {

	    vehiculo.setAnio(getAnio());
	    vehiculo.setCategoriaVehiculo(getCategoriaVehiculo());
	    vehiculo.setCliente(cliente);
	    vehiculo.setCodigoBarra(getCodigoBarra());
	    vehiculo.setColorVehiculo(getColorVehiculo());
	    vehiculo.setModeloVehiculo(getModeloVehiculo());
	    vehiculo.setPatente(getPatente());

	    getVehiculoService().save(vehiculo);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se agregó correctamente el vehiculo con patente: " + vehiculo.getPatente(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return "perfilcliente"; // Hay q modificar el path xq llevara otro template

	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el vehículo con patente: " + vehiculo.getPatente(),
		    "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return "../transaccionerror"; // Hay q modificar el path xq llevara otro template
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

    public ColorVehiculo getColorVehiculoNulo() {
	return colorVehiculoNulo;
    }

    public void setColorVehiculoNulo(ColorVehiculo colorVehiculoNulo) {
	this.colorVehiculoNulo = colorVehiculoNulo;
    }

}