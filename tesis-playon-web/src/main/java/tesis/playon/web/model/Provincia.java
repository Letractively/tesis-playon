package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los diferentes provincias.
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
    @Column(name = "provinciaID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paisID")
    private Pais pais;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provincia")
    private Set<Localidad> localidades = new HashSet<Localidad>(0);

    public Provincia() {
    }

    public Provincia(String nombre, Pais pais) {
	this.nombre = nombre;
	this.pais = pais;
    }

    public Provincia(String nombre, Pais pais, Set<Localidad> localidades) {
	this.nombre = nombre;
	this.pais = pais;
	this.localidades = localidades;
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

    public Pais getPais() {
	return pais;
    }

    public void setPais(Pais pais) {
	this.pais = pais;
    }

    public Set<Localidad> getLocalidades() {
	return localidades;
    }

    public void setLocalidades(Set<Localidad> localidades) {
	this.localidades = localidades;
    }

    @Override
    public String toString() {
	return "Provincia [provinciaID=" + id + ", nombre=" + nombre + "]";
    }

}