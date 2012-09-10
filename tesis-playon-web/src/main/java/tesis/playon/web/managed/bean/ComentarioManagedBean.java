package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.Comentario;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IComentarioService;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "comentarioMB")
@RequestScoped
public class ComentarioManagedBean implements Serializable {

    private static final long serialVersionUID = 6773490680356877684L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;

    @ManagedProperty(value = "#{ComentarioService}")
    IComentarioService comentarioService;

    private static Playa playaSelected;

    private List<Comentario> comentariosList;

    private List<Comentario> comentariosListPerfil;

    // ATRIBUTOS PARA LA CREACION DE UN COMENTARIO
    private String comentario;

    @PostConstruct
    public void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    comentariosList = getComentarioService().findByPlaya(user.getPlaya());
	}
    }

    public void addComentario() {
	Comentario comentario = null;
	try {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    String userName = facesContext.getExternalContext().getRemoteUser();
	    Usuario user = getUsuarioService().findByNombreUsuario(userName);
	    if (user != null) {
		if (user.getPlaya() == null) {
		    comentario = new Comentario(user, getComentario(), playaSelected, new Boolean(false));
		} else {
		    comentario = new Comentario(user, getComentario(), user.getPlaya(), new Boolean(false));
		}
		
		getComentarioService().save(comentario);
		
		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró exitosamente su comentario, ",
				"Debe debe aguardar su auditoría"));
	    } else {
		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudó registrar su comentario",
				"¡Debe iniciar sesión para llevar a cabo esta acción!"));
	    }
	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(
		    null,
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudó registrar su comentario",
			    "Disculpe las molestias ocacionadas"));
	}
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
	return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
	this.perfilPlayaService = perfilPlayaService;
    }

    public IComentarioService getComentarioService() {
	return comentarioService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public void setComentarioService(IComentarioService comentarioService) {
	this.comentarioService = comentarioService;
    }

    public Playa getPlayaSelected() {
	if (playaSelected != null) {
	    comentariosListPerfil = getComentarioService().findByPlaya(playaSelected);
	}
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	ComentarioManagedBean.playaSelected = playaSelected;
    }

    public List<Comentario> getComentariosList() {
	return comentariosList;
    }

    public void setComentariosList(List<Comentario> comentariosList) {
	this.comentariosList = comentariosList;
    }

    public List<Comentario> getComentariosListPerfil() {
	if (playaSelected != null) {
	    comentariosListPerfil = getComentarioService().findByPlaya(playaSelected);
	}
	return comentariosListPerfil;
    }

    public void setComentariosListPerfil(List<Comentario> comentariosListPerfil) {
	this.comentariosListPerfil = comentariosListPerfil;
    }

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }
}
