package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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

    private String descripcion;

    public String addRolUsuario() {
	try {
	    RolUsuario rolUsuario = new RolUsuario();
	    rolUsuario.setNombre(getNombre());
	    rolUsuario.setDescripcion(getDescripcion());
	    getRolUsuarioService().save(rolUsuario);
	    return LISTA_ROL_USUARIOS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
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

}