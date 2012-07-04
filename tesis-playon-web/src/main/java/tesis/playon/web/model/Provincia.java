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
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los diferentes Provincias.
 * 
 * @author gmorales
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "provincia", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre"),
	@UniqueConstraint(columnNames = "paisID") })
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "provinciaID", nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paisID", nullable = false)
    private Pais pais;

    public Provincia() {
    }

    public Provincia(Integer id, String nombre, Pais pais) {
	this.id = id;
	this.nombre = nombre;
	this.pais = pais;
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

    public Pais getPais() {
	return pais;
    }

    public void setPais(Pais pais) {
	this.pais = pais;
    }

    @Override
    public String toString() {
	return "Provincia [paisID=" + id + ", nombre=" + nombre + "]";
    }

}