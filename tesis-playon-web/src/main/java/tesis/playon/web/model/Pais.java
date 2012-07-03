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
 * Clase de negocio que contiene los diferentes Países.
 * 
 * @author gmorales
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "Pais", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nombre;
    
    private HashSet<Provincia> provincias;

    /**
     * Constructor con parámetros.
     * 
     * @param id
     * @param nombre
     */
    public Pais(Integer id, String nombre) {
	this.id = id;
	this.nombre = nombre;
    }

    /**
     * Devuelve el ID del objeto.
     * 
     * @return El ID del objeto.
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "paisID", nullable = false)
    public Integer getId() {
	return id;
    }

    /**
     * Devuelve el nombre del objeto.
     * 
     * @return El nombre del objeto.
     */
    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    public String getNombre() {
	return nombre;
    }

    /**
     * Setea un nuevo nombre al objeto.
     * 
     * @param nombre
     *            El nombre del objeto.
     */
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "price_tag_section", joinColumns = { @JoinColumn(name = "price_tag_id", unique = true) }, inverseJoinColumns = { @JoinColumn(name = "section_id") })
    public HashSet<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(HashSet<Provincia> provincias) {
        this.provincias = provincias;
    }
    
    @Override
    public String toString() {
	return "País [paisID=" + id + ", nombre=" + nombre + "]";
    }

}
