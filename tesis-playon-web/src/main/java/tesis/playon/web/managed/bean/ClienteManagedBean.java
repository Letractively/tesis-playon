/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "clienteMB")
@RequestScoped
public class ClienteManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_CLIENTES = "clientelist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    List<Cliente> clienteList;

    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

    private Integer nroCliente;

    private String domicilio;

    private String telefono;

    private Integer nroCuenta;

    private float saldo;

    private Date fechaCreacion;

    private Cliente cliente;

    private Barrio barrio;

    private CuentaCliente cuentaCliente;

    private Usuario usuario;

    private static Cliente clienteSelected;

    public String addClienteAdmin() {
	try {
	    Cliente cliente = new Cliente();
	    Usuario usuario = addUsuario();
	    CuentaCliente cuenta = addCuentaCliente();

	    cliente.setBarrio(getBarrio());
	    cliente.setCuentaCliente(cuenta);
	    cliente.setDomicilio(getDomicilio());
	    cliente.setTelefono(getTelefono());
	    cliente.setUsuario(usuario);
	    cliente.setNroCliente(cliente.getNroCliente());

	    getClienteService().save(cliente);
	    cliente = getClienteService().findByNumeroCliente(cliente.getNroCliente());
	    cuenta.setCliente(cliente);
	    getCuentaClienteService().update(cuenta);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
		    "Se agregó correctamente el cliente: "+ cliente.getUsuario().getApellido() + " "
			    + cliente.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_CLIENTES;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el cliente: " + cliente.getUsuario().getApellido() + " "
			    + cliente.getUsuario().getNombre(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }
    
    public String addSolicitudCliente() {
	try {
	    Cliente cliente = new Cliente();
	    Usuario usuario = addUsuario();
	    CuentaCliente cuenta = addCuentaCliente();

	    cliente.setBarrio(getBarrio());
	    cliente.setCuentaCliente(cuenta);
	    cliente.setDomicilio(getDomicilio());
	    cliente.setTelefono(getTelefono());
	    cliente.setUsuario(usuario);
	    cliente.setNroCliente(cliente.getNroCliente());

	    getClienteService().save(cliente);
	    cliente = getClienteService().findByNumeroCliente(cliente.getNroCliente());
	    cuenta.setCliente(cliente);
	    getCuentaClienteService().update(cuenta);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
		    "Se agregó correctamente el cliente: "+ cliente.getUsuario().getApellido() + " "
			    + cliente.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return "solicitudclienteend";
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el cliente: " + cliente.getUsuario().getApellido() + " "
			    + cliente.getUsuario().getNombre(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
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

    public CuentaCliente addCuentaCliente() {
	try {
	    CuentaCliente cuenta = new CuentaCliente();
	    cuenta.setFechaCreacion(cuenta.getFechaCreacion());
	    cuenta.setNroCuenta(cuenta.getNroCuenta());
	    cuenta.setSaldo(getSaldo());
	    cuenta.setCliente(getCliente());
	    getCuentaClienteService().save(cuenta);
	    return cuenta;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String deleteClienteAdmin(Cliente clienteSelected) {
	try {
	    CuentaCliente cuenta = clienteSelected.getCuentaCliente();
	    getCuentaClienteService().delete(cuenta);
	    Usuario usuario = clienteSelected.getUsuario();
	    getClienteService().delete(clienteSelected);
	    getUsuarioService().delete(usuario);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
		    "Se borró el cliente: " + clienteSelected.getUsuario().getApellido() + " "
	    + clienteSelected.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_CLIENTES;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo borrar el cliente: " + clienteSelected.getUsuario().getApellido() + " "
			    + clienteSelected.getUsuario().getNombre(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }
    
    public String updateClienteAdmin(Cliente cliente){
	clienteSelected= cliente;
	return "clienteeditadmin";
    }
    
    public String updateClienteAdmin(){
	try {
	    Usuario usuario = clienteSelected.getUsuario();
	    getUsuarioService().update(usuario);
	    getClienteService().update(clienteSelected);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
		    "La cliente se modificó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_CLIENTES;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error,el cliente no se pudo modificar"
		    , "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void updateCliente(Cliente cliente) {
	getClienteService().update(cliente);
    }

    public void reset() {
	this.setBarrio(null);
	this.setCuentaCliente(null);
	this.setDomicilio("");
	this.setNroCliente(0);
	this.setTelefono("");
	this.setUsuario(null);
	this.setNombre("");
	this.setApellido("");
	this.setEmail("");
	this.setNroDoc(0);
	this.setPassword("");
	this.setNombreUser("");
	ClienteManagedBean.clienteSelected=null;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IClienteService getClienteService() {
	return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

    public ICuentaClienteService getCuentaClienteService() {
	return cuentaClienteService;
    }

    public void setCuentaClienteService(ICuentaClienteService cuentaClienteService) {
	this.cuentaClienteService = cuentaClienteService;
    }

    public List<Cliente> getClienteList() {
	clienteList = new ArrayList<Cliente>();
	clienteList.addAll(getClienteService().findAll());
	return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
	this.clienteList = clienteList;
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

    public Integer getNroCliente() {
	return nroCliente;
    }

    public void setNroCliente(Integer nroCliente) {
	this.nroCliente = nroCliente;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public CuentaCliente getCuentaCliente() {
	return cuentaCliente;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
	this.cuentaCliente = cuentaCliente;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Integer getNroCuenta() {
	return nroCuenta;
    }

    public void setNroCuenta(Integer nroCuenta) {
	this.nroCuenta = nroCuenta;
    }

    public float getSaldo() {
	return saldo;
    }

    public void setSaldo(float saldo) {
	this.saldo = saldo;
    }

    public Date getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Cliente getClienteSelected() {
	return clienteSelected;
    }

    public void setClienteSelected(Cliente clienteSelected) {
	ClienteManagedBean.clienteSelected = clienteSelected;
    }
    public String modificarClienteAdmin(Cliente cliente){
	clienteSelected= cliente;
	return "clienteeditadmin";
    }
}