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

import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IRolUsuarioService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "empleadoMB")
@RequestScoped
public class EmpleadoManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_EMPLEADOS = "empleadolist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{EmpleadoService}")
    IEmpleadoService empleadoService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{RolUsuarioService}")
    IRolUsuarioService rolUsuarioService;

    @ManagedProperty(value = "#{CargoEmpleadoService}")
    ICargoEmpleadoService cargoEmpleadoService;

    @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    List<Empleado> empleadoList;

    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

    private Integer legajo;

    private String domicilio;

    private String telefono;

    // private Integer nroCuenta;

    private Date fechaCreacion;

    private Empleado empleado;

    private CargoEmpleado cargoEmpleado;

    private Usuario usuario;

    private static Empleado empleadoSelected;

    public String addEmpleadoAdmin() {
	try {
	    Empleado empleado = new Empleado();
	    Usuario usuario = addUsuario();
	    // CargoEmpleado cargo = addCargoEmpleado();

	    empleado.setCargoEmpleado(cargoEmpleado);
	    empleado.setLegajo(getLegajo());
	    empleado.setUsuario(usuario);
	    getEmpleadoService().save(empleado);
	    empleado = getEmpleadoService().findByLegajoEmpleado(empleado.getLegajo());

	    RolesPorUsuario rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_CLIENT");
	    getRolesPorUsuarioService().save(rp);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente el cliente: "
		    + empleado.getUsuario().getApellido() + " " + empleado.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_EMPLEADOS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el empleado: " + empleado.getUsuario().getApellido() + " "
			    + empleado.getUsuario().getNombre(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String addEmpleado() {
	Empleado empleado = new Empleado();
	Usuario usuario = addUsuario();
	try {
	    empleado.setCargoEmpleado(getCargoEmpleado());
	    empleado.setLegajo(getLegajo());
	    empleado.setUsuario(usuario);

	    getUsuarioService().save(usuario);
	    getEmpleadoService().save(empleado);

	    RolesPorUsuario rp;
	    if ((empleado.getCargoEmpleado()).getNombre().equals("Playero"))
		rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_PLAYA_EMPLEADO");
	    else {
		if ((empleado.getCargoEmpleado()).getNombre().equals("Gerente General"))
		    rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_PLAYA_GERENTE");
		else {
		    rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_PLAYA_EMPLEADO");
		}
	    }
	    getRolesPorUsuarioService().save(rp);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente el empleado: "
		    + empleado.getUsuario().getApellido() + " " + empleado.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "empleadoend";
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el empleado: " + empleado.getUsuario().getApellido() + " "
			    + empleado.getUsuario().getNombre(), "Por favor, inténtelo más tarde.");
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

	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    String userName = facesContext.getExternalContext().getRemoteUser();
	    Usuario user = getUsuarioService().findByNombreUsuario(userName);

	    usuario.setPlaya(user.getPlaya());

	    return usuario;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String deleteEmpleadoAdmin(Empleado empleadoSelected) {
	try {
	    CargoEmpleado cargo = empleadoSelected.getCargoEmpleado();
	    getCargoEmpleadoService().delete(cargo);
	    Usuario usuario = empleadoSelected.getUsuario();
	    getEmpleadoService().delete(empleadoSelected);
	    getUsuarioService().delete(usuario);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se borró el empleado: "
		    + empleadoSelected.getUsuario().getApellido() + " " + empleadoSelected.getUsuario().getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_EMPLEADOS;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo borrar el empleado: " + empleadoSelected.getUsuario().getApellido() + " "
			    + empleadoSelected.getUsuario().getNombre(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String updateEmpleadoAdmin() {
	try {
	    Usuario usuario = empleadoSelected.getUsuario();
	    getUsuarioService().update(usuario);
	    getEmpleadoService().update(empleadoSelected);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "El empleado se modificó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_EMPLEADOS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error,el cliente no se pudo modificar", "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void reset() {
	this.setCargoEmpleado(null);
	this.setUsuario(null);
	this.setNombre("");
	this.setApellido("");
	this.setEmail("");
	this.setNroDoc(null);
	this.setPassword("");
	this.setNombreUser("");
	EmpleadoManagedBean.empleadoSelected = null;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IEmpleadoService getEmpleadoService() {
	return empleadoService;
    }
    
    public void setEmpleadoService(IEmpleadoService empleadoService) {
	this.empleadoService = empleadoService;
    }

    public ICargoEmpleadoService getCargoEmpleadoService() {
	return cargoEmpleadoService;
    }

    public void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
	this.cargoEmpleadoService = cargoEmpleadoService;
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

    public List<Empleado> getEmpleadoList() {
	empleadoList = new ArrayList<Empleado>();
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	
	List<Usuario> users = new ArrayList<Usuario>();
	users.addAll(getUsuarioService().findByPlaya(user.getPlaya()));
	for (Usuario usuario : users) {
	    empleadoList.add(getEmpleadoService().findByUsuario(usuario));
	}
	return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
	this.empleadoList = empleadoList;
    }

    public CargoEmpleado getCargoEmpleado() {
        return cargoEmpleado;
    }

    public void setCargoEmpleado(CargoEmpleado cargoEmpleado) {
        this.cargoEmpleado = cargoEmpleado;
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

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public Empleado getEmpleado() {
        return empleado;
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

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Date getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

    public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
    }

    public CargoEmpleado addCargoEmpleado() {
	try {
	    CargoEmpleado cargo = new CargoEmpleado();
	    cargo.setNombre(cargo.getNombre());
	    cargo.setId(cargo.getId());
	    return cargo;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public Empleado getEmpleadoSelected() {
	return empleadoSelected;
    }

    public void setEmpleadoSelected(Empleado empleadoSelected) {
	EmpleadoManagedBean.empleadoSelected = empleadoSelected;
    }
}