/**
 * 
 */
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

    // ATRIBUTOS PARA LA CREACION DE UN COMENTARIO
    private String comentario;

    @PostConstruct
    // METODO PARA INICIALIZAR TODOS LOS ATRIBUTOS
    public void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    comentariosList = getComentarioService().findByPlaya(user.getPlaya());
	}
    }

    public String addComentario() {
	Comentario comentario = null;
	try {
	    comentario = new Comentario();
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    String userName = facesContext.getExternalContext().getRemoteUser();
	    Usuario user = getUsuarioService().findByNombreUsuario(userName);
	    if (user != null && user.getPlaya() != null) {
		
		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró exitosamente su comentario, ",
				"Debe debe aguardar su auditoría"));
	    } else {
		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudó registrar su comentario",
				"¡Debe iniciar sesión para llevar a cabo esta acción!"));
	    }
	    
	    return "comentariolist";
	    
	} catch (Exception e) {

	}
	return "/error";
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

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }
}
