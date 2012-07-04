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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los diferentes Paises.
 * 
 * @author gmorales
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "pais", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "paisID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
    private Set<Provincia> provincias = new HashSet<Provincia>(0);

    public Pais() {
    }

    public Pais(String nombre) {
	this.nombre = nombre;
    }

    public Pais(String nombre, Set<Provincia> provincias) {
	this.nombre = nombre;
	this.provincias = provincias;
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

    public Set<Provincia> getProvincias() {
	return provincias;
    }

    public void setProvincias(Set<Provincia> provincias) {
	this.provincias = provincias;
    }

    @Override
    public String toString() {
	return "Pais [paisID=" + id + ", nombre=" + nombre + "]";
    }

}
