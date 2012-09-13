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
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.Mail;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.IRolUsuarioService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.ITipoDocService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;

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

    private int importe;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{RolUsuarioService}")
    IRolUsuarioService rolUsuarioService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    @SuppressWarnings("unused")
    private SelectItem[] tipoDocOptions;

    @ManagedProperty(value = "#{TipoDocService}")
    ITipoDocService tipoDocService;

    List<Cliente> clienteList;

    private Mail mail;

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

    private float saldo = 0;;

    private Date fechaCreacion;

    private Cliente cliente;

    private Barrio barrio;

    private CuentaCliente cuentaCliente;

    private CuentaCliente cuentaClienteSelected;

    private Usuario usuario;

    private String nombreUsuario;

    private List<Cliente> filteredClientes;

    private static Cliente clienteSelected;

    private NotificadorUtil notificador;

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

	    mail = new Mail();
	    mail.setAsunto("Felicitaciones " + getNombreUser() + "ya sos usuario de PLAYON!");
	    mail.setDestinatario(getEmail());
	    mail.setMensaje("Estimado "
		    + getNombre()
		    + " usted ya es usuario de PLAYON RED DE PLAYAS.\n\n Acceda desde aqu� y busque su playa de estacionamiento!\n\n http://localhost:8080/tesis-playon-web/");
	    notificador = new NotificadorUtil();
	    notificador.enviar(mail);
	    RolesPorUsuario rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_CLIENT");
	    getRolesPorUsuarioService().save(rp);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente el cliente: "
		    + cliente.getUsuario().getApellido() + " " + cliente.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_CLIENTES;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el cliente: " + cliente.getUsuario().getApellido() + " "
			    + cliente.getUsuario().getNombre(), "Por favor, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el cliente. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public void cargarSaldo() {
	try {
	    float saldoCliente = cliente.getCuentaCliente().getSaldo();
	    cliente.getCuentaCliente().setSaldo(saldoCliente + saldo);
	    getCuentaClienteService().update(cliente.getCuentaCliente());

	    FacesContext.getCurrentInstance().addMessage(
		    null,
		    new FacesMessage(FacesMessage.SEVERITY_INFO, "Transacción exitosa", "Se agregó a su cuenta $"
			    + getSaldo() + " argentinos. ¡Muchas Gracias!"));

	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
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

	    RolesPorUsuario rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_CLIENT");
	    getRolesPorUsuarioService().save(rp);

	    mail = new Mail();
	    mail.setAsunto("Felicitaciones " + getNombreUser() + "ya sos usuario de PLAYON!");
	    mail.setDestinatario(getEmail());
	    mail.setMensaje("Estimado "
		    + getNombre()
		    + " usted ya es usuario de PLAYON RED DE PLAYAS.\n\n Acceda desde aqu� y busque su playa de estacionamiento!\n\n http://localhost:8080/tesis-playon-web/");
	    notificador = new NotificadorUtil();
	    notificador.enviar(mail);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente el cliente: "
		    + cliente.getUsuario().getApellido() + " " + cliente.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return "solicitudclienteend";
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el cliente: " + cliente.getUsuario().getApellido() + " "
			    + cliente.getUsuario().getNombre(), "Por favor, intentelo mas tarde.");
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
	    usuario.setEnable(new Boolean(true));
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

    public String deleteClienteAdmin() {
	try {

	    Usuario usuario = clienteSelected.getUsuario();
	    usuario.setEnable(new Boolean(false));

	    getUsuarioService().update(usuario);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se borró el cliente: "
		    + clienteSelected.getUsuario().getApellido() + " " + clienteSelected.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_CLIENTES;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo borrar el cliente: " + clienteSelected.getUsuario().getApellido() + " "
			    + clienteSelected.getUsuario().getNombre(), "Por favor, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String updateClienteAdmin(Cliente cliente) {
	clienteSelected = cliente;
	return "clienteeditadmin";
    }

    public String updateClienteAdmin() {
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
		    "Error,el cliente no se pudo modificar", "Por favor, intentelo mas tarde.");
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
	ClienteManagedBean.clienteSelected = null;
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

    public IRolesPorUsuarioService getRolesPorUsuarioService() {
	return rolesPorUsuarioService;
    }

    public void setRolesPorUsuarioService(IRolesPorUsuarioService rolesPorUsuarioService) {
	this.rolesPorUsuarioService = rolesPorUsuarioService;
    }

    public IRolUsuarioService getRolUsuarioService() {
	return rolUsuarioService;
    }

    public void setRolUsuarioService(IRolUsuarioService rolUsuarioService) {
	this.rolUsuarioService = rolUsuarioService;
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

    public String getNombreUsuario() {
	return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
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

    public int getImporte() {
	return importe;
    }

    public void setImporte(int importe) {
	this.importe = importe;
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

    public String modificarClienteAdmin(Cliente cliente) {
	clienteSelected = cliente;
	return "clienteeditadmin";
    }

    public CuentaCliente getCuentaClienteSelected() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario usuario = getUsuarioService().findByNombreUsuario(userName);
	Cliente cliente = getClienteService().findByUsuario(usuario);
	cuentaClienteSelected = cliente.getCuentaCliente();
	return cuentaClienteSelected;
    }

    public void setCuentaClienteSelected(CuentaCliente cuentaClienteSelected) {
	this.cuentaClienteSelected = cuentaClienteSelected;
    }

    public SelectItem[] getTipoDocOptions() {
	List<TipoDoc> tipoDoc = new ArrayList<TipoDoc>();
	tipoDoc.addAll(getTipoDocService().findAll());
	tipoDocOptions = new SelectItem[tipoDoc.size() + 1];
	SelectItem[] options = new SelectItem[tipoDoc.size() + 1];
	options[0] = new SelectItem("", "Todos");

	for (int i = 0; i < tipoDoc.size(); i++) {
	    options[i + 1] = new SelectItem(tipoDoc.get(i), tipoDoc.get(i).getNombre());
	}
	return options;
    }

    public void setTipoDocOptions(SelectItem[] tipoDocOptions) {
	this.tipoDocOptions = tipoDocOptions;
    }

    public ITipoDocService getTipoDocService() {
	return tipoDocService;
    }

    public void setTipoDocService(ITipoDocService tipoDocService) {
	this.tipoDocService = tipoDocService;
    }

    public List<Cliente> getFilteredClientes() {
	return filteredClientes;
    }

    public void setFilteredClientes(List<Cliente> filteredClientes) {
	this.filteredClientes = filteredClientes;
    }

}