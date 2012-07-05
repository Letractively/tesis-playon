package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los diferentes roles de usuario.
 * 
 * @author gmorales
 * 
 */
@Entity
@Table(name = "rol_usuario", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class RolUsuario implements Serializable {

    private static final long serialVersionUID = 4725165248854057805L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rolUsuarioID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public RolUsuario() {
    }

    public RolUsuario(String nombre) {
	this.nombre = nombre;
    }

    public RolUsuario(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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

    @Override
    public String toString() {
	return "RolUsuario [rolUsuarioID=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

}
