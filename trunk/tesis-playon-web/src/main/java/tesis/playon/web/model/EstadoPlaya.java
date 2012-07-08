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
 * 
 * @author alejandro
 * @date 08/07/2012
 */
@Entity
@Table(name = "estadoPlaya", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class EstadoPlaya implements Serializable {

    private static final long serialVersionUID = 9126225080245683593L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadoPlayaID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public EstadoPlaya() {
	super();
    }

    public EstadoPlaya(String nombre, String descripcion) {
	super();
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    public Integer getId() {
	return id;
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
	return "Estado Playa [estadoPlayaID=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }
}
