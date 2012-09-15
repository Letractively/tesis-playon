/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.Cliente;
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
    
    private Cliente cliente;
    
    private Usuario usuario;
    
    @PostConstruct
    private void init(){
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if(user!= null){
	    this.usuario = user;
	    this.cliente = getClienteService().findByUsuario(user);
	    WriteImage.getFotoPerfilCliente(user);
	}
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

}
