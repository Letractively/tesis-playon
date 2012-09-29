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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Favorito;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IFavoritoService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "favoritoMB")
@RequestScoped
public class FavoritoManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{FavoritoService}")
    IFavoritoService favoritoService;

    private MenuModel model;

    private static Playa playaSelected;

    private Cliente cliente;

    private List<Favorito> favoritosListPerfil;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null) {
	    this.cliente = getClienteService().findByUsuario(user);
	    favoritosListPerfil = new ArrayList<Favorito>();
	    favoritosListPerfil = getFavoritoService().findByCliente(this.cliente);
	    menuFavorito();
	}
    }

    public void addFavorito() {
	Favorito favorito = null;
	try {
	    if (playaSelected != null) {
		if (!getFavoritoService().isFavorito(cliente, playaSelected)) {
		    favorito = new Favorito(playaSelected, cliente);
		    getFavoritoService().save(favorito);
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			    "Se agregó correctamente la playa a favoritos", "");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			    "Ya se encontraba registrada la playa en favoritos", "");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}

	    }
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "No se pudo agregar la playa a favoritos.\nDisculpe las molestias ocacionadas", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    private void menuFavorito() {
	model = new DefaultMenuModel();

	// SubMenu favorito
	Submenu submenu = new Submenu();
	submenu.setLabel("Playas Favoritas");

	if (favoritosListPerfil != null) {
	    for (Favorito favorito : favoritosListPerfil) {
		MenuItem item = new MenuItem();
		item.setValue(favorito.getPlaya().getNombreComercial());
		int idPlaya = favorito.getPlaya().getId();
		item.setUrl("/viewperfilplayaid.html?id="+idPlaya);
		item.setIcon("ui-icon-star");
		submenu.getChildren().add(item);
	    }
	}

	model.addSubmenu(submenu);
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

    public IFavoritoService getFavoritoService() {
	return favoritoService;
    }

    public void setFavoritoService(IFavoritoService favoritoService) {
	this.favoritoService = favoritoService;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public List<Favorito> getFavoritosListPerfil() {
	return favoritosListPerfil;
    }

    public void setFavoritosListPerfil(List<Favorito> favoritosListPerfil) {
	this.favoritosListPerfil = favoritosListPerfil;
    }

    public Playa getPlayaSelected() {
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	FavoritoManagedBean.playaSelected = playaSelected;
    }

    public MenuModel getModel() {
	return model;
    }

    public void setModel(MenuModel model) {
	this.model = model;
    }

}