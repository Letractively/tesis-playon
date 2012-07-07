package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase de negocio que contiene los comentarios sobre una playa
 * 
 * @author gmorales
 * 
 */
@Entity
@Table(name = "usuario_sistema", catalog = "tesis_playon")
public class UsuarioSistema implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuarioSistemaID")
    private Integer id;

    @Column(name = "nombreComercial")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playaID")
    private Playa playa;

    public UsuarioSistema() {
    }

    public UsuarioSistema(String nombre, String descripcion, Playa playa) {
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.playa = playa;
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

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    @Override
    public String toString() {
	return "PerfilPlaya [perfilPlayaID=" + id + ", nombreComercial=" + nombre + ", descripcion=" + descripcion
		+ "]";
    }

}
