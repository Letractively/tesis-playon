/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.WriteImage;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "perfilClienteMB")
@RequestScoped
public class PerfilClienteManagedBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;
    
    private String apellido;

    private String nombre;

    private String email;

    private TipoDoc tipoDoc;
    
    private Integer nroDocumento;

    private String domicilio;

    private String telefono;
    
    private Barrio barrio;
    
    private Cliente cliente;
    
    private Usuario usuario;
    
    private UploadedFile fotoPerfilFile;
    
    @PostConstruct
    private void init(){
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if(user!= null){
	    this.usuario = user;
	    this.cliente = getClienteService().findByUsuario(user);
	    WriteImage.getFotoPerfilCliente(user);
	    this.apellido = user.getApellido();
	    this.nombre = user.getNombre();
	    this.email = user.getEmail();
	    this.tipoDoc = user.getTipoDoc();
	    this.nroDocumento = user.getNroDoc();
	    this.domicilio = cliente.getDomicilio();
	    this.telefono = cliente.getTelefono();
	    this.barrio = cliente.getBarrio();
	}
    }
    
    public String upload() {
	try {
	    this.cliente.getUsuario().setFotoPerfil(fotoPerfilFile.getContents());
	    getUsuarioService().update(this.cliente.getUsuario());
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se modificó exitosamente su foto de perfil", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    
	    WriteImage.getFotoPerfilCliente(this.cliente.getUsuario());
	    
	    return "perfilclienteedit";
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se pudo cargar su foto de perfil",
		    "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return "/error";
    }

    public IClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public TipoDoc getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
        this.tipoDoc = tipoDoc;
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

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public UploadedFile getFotoPerfilFile() {
        return fotoPerfilFile;
    }

    public void setFotoPerfilFile(UploadedFile fotoPerfilFile) {
        this.fotoPerfilFile = fotoPerfilFile;
    }

}
