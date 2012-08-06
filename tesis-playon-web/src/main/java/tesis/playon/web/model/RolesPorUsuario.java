package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author garribere
 * 
 */
@Entity
@Table(name = "roles_por_usuario", catalog = "tesis_playon")
public class RolesPorUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rolesPorUsuarioID")
    private Integer id;

    @Column(name = "usuario")
    private Usuario usuario;

    @Column(name = "rolUsuario")
    private RolUsuario rol;

    public RolesPorUsuario() {
    }

    public RolesPorUsuario(Usuario usuario, RolUsuario rol) {
	this.usuario = usuario;
	this.rol = rol;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public RolUsuario getRol() {
	return rol;
    }

    public void setRol(RolUsuario rol) {
	this.rol = rol;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	RolesPorUsuario otroRol = (RolesPorUsuario) object;
	if (id != otroRol.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "RolesPorUsuario:\t [RolPorUserID= " + id + ", Usuario= " + usuario.getNombre() + "ROL: "
		+ rol.getNombre() + "]";
    }

}
