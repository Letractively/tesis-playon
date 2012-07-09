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
 * Clase de negocio que contiene los diferentes localidades.
 * 
 * @author gmorales
 * @date 05/07/2012
 * 
 */
@Entity
@Table(name = "localidad", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre"),
	@UniqueConstraint(columnNames = "provinciaID") })
public class Localidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "localidadID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinciaID")
    private Provincia provincia;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localidad")
    private Set<Barrio> barrios = new HashSet<Barrio>(0);

    public Localidad() {
    }

    public Localidad(String nombre, Provincia provincia) {
	this.nombre = nombre;
	this.provincia = provincia;
    }

    public Localidad(String nombre, Provincia provincia, Set<Barrio> barrios) {
	this.nombre = nombre;
	this.provincia = provincia;
	this.barrios = barrios;
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

    public Provincia getProvincia() {
	return provincia;
    }

    public void setProvincia(Provincia provincia) {
	this.provincia = provincia;
    }

    public Set<Barrio> getBarrios() {
	return barrios;
    }

    public void setBarrios(Set<Barrio> barrios) {
	this.barrios = barrios;
    }

    @Override
    public String toString() {
	return "Localidad:\t [localidadID= " + id + ", nombre= " + nombre + ", "+ provincia.toString() + "]";
    }

}
