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

import tesis.playon.web.model.RolUsuario;
import tesis.playon.web.service.IRolUsuarioService;

@ManagedBean(name = "rolUsuarioMB")
@RequestScoped
public class RolUsuarioManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_ROL_USUARIOS = "rolusuariolist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{RolUsuarioService}")
    IRolUsuarioService rolUsuarioService;

    List<RolUsuario> rolUsuarioList;

    private String nombre;
    
    private static RolUsuario rolUsuarioSelected;

    private String descripcion;

    public String addRolUsuario() {
    	RolUsuario rolUsuario = new RolUsuario();
	    try {
		    
		    rolUsuario.setNombre(getNombre());
		    rolUsuario.setDescripcion(getDescripcion());
		    getRolUsuarioService().save(rolUsuario);
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente el Rol: "
				 + rolUsuario.getNombre(), "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			    
		    return LISTA_ROL_USUARIOS;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				    "Error, no se pudo agregar el rol: " + rolUsuario.getNombre(),
				    "Por favor, intentelo mas tarde.");
			    FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return ERROR;
    }
    
    public String deleteRolUsuarioAdmin() {
    	RolUsuario rolUsuario = rolUsuarioSelected;
    	try {
    	    
    		getRolUsuarioService().delete(rolUsuario);
    	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja el Rol: "
    		    + rolUsuario.getNombre(), "");
    	    FacesContext.getCurrentInstance().addMessage(null, message);
    	    return LISTA_ROL_USUARIOS;
    	} catch (Exception e) {
    	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
    		    "Error, no se pudo dar de baja el Rol: " + rolUsuario.getNombre(),
    		    "Por favor, inténtelo más tarde.");
    	    FacesContext.getCurrentInstance().addMessage(null, message);
    	}
    	return ERROR;
        }
    
    

    public void deleteRolUsuario(RolUsuario rolUsuario) {
	getRolUsuarioService().delete(rolUsuario);
    }

    public void updateRolusuario(RolUsuario rolUsuario) {
	getRolUsuarioService().update(rolUsuario);
    }

    public void reset() {
	this.setNombre("");
	this.setDescripcion("");
    }

    public IRolUsuarioService getRolUsuarioService() {
	return rolUsuarioService;
    }

    public void setRolUsuarioService(IRolUsuarioService rolUsuarioService) {
	this.rolUsuarioService = rolUsuarioService;
    }

    public List<RolUsuario> getRolUsuarioList() {
	rolUsuarioList = new ArrayList<RolUsuario>();
	rolUsuarioList.addAll(getRolUsuarioService().findAll());
	return rolUsuarioList;
    }

    public void setRolUsuarioList(List<RolUsuario> rolUsuarioList) {
	this.rolUsuarioList = rolUsuarioList;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }
    
    public RolUsuario getRolUsuarioSelected() {
    	return rolUsuarioSelected;
    }

    public void setRolUsuarioSelected(RolUsuario rolUsuarioSelected) {
    	RolUsuarioManagedBean.rolUsuarioSelected = rolUsuarioSelected;
    }
          

}