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
 * Clase de negocio que contiene los diferentes Provincias.
 * 
 * @author gmorales
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "Provincia", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "paisID", nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    public Provincia(Integer id, String nombre) {
	this.id = id;
	this.nombre = nombre;
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

    @Override
    public String toString() {
	return "Provincia [paisID=" + id + ", nombre=" + nombre + "]";
    }

}