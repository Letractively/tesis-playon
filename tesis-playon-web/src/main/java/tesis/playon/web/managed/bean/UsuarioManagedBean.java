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

import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IUsuarioService;

@ManagedBean(name = "usuarioMB")
@RequestScoped
public class UsuarioManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_USUARIOS = "usuariolist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    List<Usuario> usuarioList;

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
		    "Error, no se pudo agregar el usuario: " + usuario.getNombreUser() + " " + usuario.getNombreUser(),
		    "Por favor, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
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
	return ERROR;
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
      
}