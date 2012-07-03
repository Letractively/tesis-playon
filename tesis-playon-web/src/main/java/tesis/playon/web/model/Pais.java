package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "Pais", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "paisID", nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Provincia", joinColumns = { @JoinColumn(name = "paisID", unique = true) }, inverseJoinColumns = { @JoinColumn(name = "paisID") })
    private HashSet<Provincia> provincias = new HashSet<Provincia>(0);

    public Pais(Integer id, String nombre, HashSet<Provincia> provincias) {
	this.id = id;
	this.nombre = nombre;
	this.provincias = provincias;
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

    public HashSet<Provincia> getProvincias() {
	return provincias;
    }

    public void setProvincias(HashSet<Provincia> provincias) {
	this.provincias = provincias;
    }

    @Override
    public String toString() {
	return "Pais [paisID=" + id + ", nombre=" + nombre + ", Provincias=" + provincias.toString() + "]";
    }

}
