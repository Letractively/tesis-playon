package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "rol_usuario", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre_rol_usuario") })
public class RolUsuario implements Serializable {

    private static final long serialVersionUID = 4725165248854057805L;

    private Integer id;

    private String nombre;

    public RolUsuario() {
    }

    public RolUsuario(Integer id, String nombre) {
	this.id = id;
	this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_rol_usuario", nullable = false)
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    @Column(name = "nombre_rol_usuario", unique = true, nullable = false, length = 20)
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Override
    public String toString() {
	return "RolUsuario [id_rol_usuario=" + id + ", nombre_rol_usuario=" + nombre + "]";
    }

}
