/**
 * 
 */
package tesis.playon.web.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "perfilPlayaMB")
@SessionScoped
public class PerfilPlayaManagedBean {
    
    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;
    
    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;
    
    private PerfilPlaya perfil;

    public PerfilPlaya getPerfil() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	perfil = getPerfilPlayaService().findByPlaya(user.getPlaya());
	return perfil;
    }

    public void setPerfil(PerfilPlaya perfil) {
        this.perfil = perfil;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
        return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
        this.perfilPlayaService = perfilPlayaService;
    }

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public IPlayaService getPlayaService() {
        return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
        this.playaService = playaService;
    }

}
