/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IEstadoDenunciaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "denunciaPlayaMB")
@ViewScoped
public class DenunciaPlayaManagedBean implements Serializable{

    private static final long serialVersionUID = 1658656193254593451L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;
    
    @ManagedProperty(value = "#{EstadoDenunciaService}")
    IEstadoDenunciaService estadoDenunciaService;
    
    private static Playa playaSelected;
    
    private String motivo;
    
    
    public String denunciarPlaya()
    {
	return null;
    }

    /**
     * @return the usuarioService
     */
    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    /**
     * @param usuarioService the usuarioService to set
     */
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * @return the playaService
     */
    public IPlayaService getPlayaService() {
        return playaService;
    }

    /**
     * @param playaService the playaService to set
     */
    public void setPlayaService(IPlayaService playaService) {
        this.playaService = playaService;
    }

    /**
     * @return the estadoDenunciaService
     */
    public IEstadoDenunciaService getEstadoDenunciaService() {
        return estadoDenunciaService;
    }

    /**
     * @param estadoDenunciaService the estadoDenunciaService to set
     */
    public void setEstadoDenunciaService(IEstadoDenunciaService estadoDenunciaService) {
        this.estadoDenunciaService = estadoDenunciaService;
    }

    /**
     * @return the playaSelected
     */
    public Playa getPlayaSelected() {
        return playaSelected;
    }

    /**
     * @param playaSelected the playaSelected to set
     */
    public void setPlayaSelected(Playa playaSelected) {
        DenunciaPlayaManagedBean.playaSelected = playaSelected;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
}
