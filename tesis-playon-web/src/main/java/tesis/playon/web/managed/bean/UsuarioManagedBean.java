package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Mail;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;

@ManagedBean(name = "usuarioMB")
@RequestScoped
public class UsuarioManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_USUARIOS = "usuariolist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    List<Usuario> usuarioList;

    private String passViejo;

    private String passActual;

    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

    private Playa playa;

    private static Usuario usuarioSelected;

    public String addUsuarioAdmin() {
	Usuario usuario = new Usuario();
	try {

	    usuario.setNombre(getNombre());
	    usuario.setApellido(getApellido());
	    usuario.setEmail(getEmail());
	    usuario.setNroDoc(getNroDoc());
	    usuario.setPassword(getPassword());
	    usuario.setNombreUser(getNombreUser());
	    usuario.setTipoDoc(getTipoDoc());
	    usuario.setPlaya(getPlaya());
	    usuario.setEnable(new Boolean("true"));
	    getUsuarioService().save(usuario);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente el usuario: "
		    + usuario.getNombreUser() + " " + usuario.getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_USUARIOS;

	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el usuario: " + usuario.getNombreUser(),
		    "Por favor, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el usuario. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}

	return ERROR;
    }

    public String deleteUsuarioAdmin() {
	Usuario usuario = usuarioSelected;
	try {

	    usuario.setEnable(new Boolean("false"));

	    getUsuarioService().update(usuario);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja al usuario: "
		    + usuario.getNombreUser(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_USUARIOS;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja al usuario: " + usuario.getNombreUser(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public void deleteUsuario(Usuario usuario) {
	getUsuarioService().delete(usuario);
    }

    public void updateUsuario(Usuario usuario) {
	getUsuarioService().update(usuario);
    }

    public String updateUsuarioAdmin() {
	try {
	    getUsuarioService().update(usuarioSelected);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, usuarioSelected.getNombreUser()
		    + " se modificó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_USUARIOS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + usuarioSelected.getNombreUser() + " no se pudo modificar", "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
    catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el cliente. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String recuperarPass()
    {
	Usuario usu = new Usuario();

	try {

	    usu = getUsuarioService().findByNombreUsuario(getNombreUser());
	    Mail mail = new Mail();
	    NotificadorUtil notificador = new NotificadorUtil();
	    mail.setAsunto("Contraseña de Playon - Red de plaayas");
	    mail.setDestinatario(usu.getEmail());
	    mail.setMensaje("Estimado: " + usu.getNombre()
		    + " su contraseña de Playon - Red de playas es la siguiente: " + usu.getPassword());
	    notificador.enviar(mail);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se envio correctamente su contrase�a: " + usu.getApellido() + " " + usu.getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "recuperarpasswordend";
	}

	catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error el usuario no existe, Por favor, inténtelo más tarde.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();

	}
	return "ERROR";

    }

    public String modificarPassword() 
    {
		Usuario usu;
		usu = getUsuario();
		try {

	    usu.setPassword(getPassword());

	    getUsuarioService().update(usu);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico contrase�a: "
		    + usu.getApellido() + " " + usu.getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "cambiarpasswordend";

		}
		catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "No se pudo modificar su contrase�a, Por favor, inténtelo más tarde.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	    return "ERROR";
		}
    }

    public String modificarUsuarioAdmin(Usuario usuario) {
	usuarioSelected = usuario;
	return "usuarioeditadmin";
    }

    public void reset() {
	this.setNombre("");
	this.setApellido("");
	this.setEmail("");
	this.setNroDoc(0);
	this.setPassword("");
	this.setNombreUser("");
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public List<Usuario> getUsuarioList() {
	usuarioList = new ArrayList<Usuario>();
	usuarioList.addAll(getUsuarioService().findAll());
	return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
	this.usuarioList = usuarioList;
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

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Usuario getUsuarioSelected() {
	return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
	UsuarioManagedBean.usuarioSelected = usuarioSelected;
    }

    public String getPassViejo() {
	return passViejo;
    }

    public void setPassViejo(String passViejo) {
	this.passViejo = passViejo;
    }

    public String getPassActual() {
	passActual = usuarioSelected.getPassword();
	return passActual;
    }

    public void setPassActual(String passActual) {
	this.passActual = passActual;
    }

    public Usuario getUsuario() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario usuario = getUsuarioService().findByNombreUsuario(userName);

	return usuario;
    }

}