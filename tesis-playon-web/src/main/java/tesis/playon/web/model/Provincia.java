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

    private Integer id;

    private String nombre;

    private Pais pais;

    /**
     * Constructor con parámetros.
     * 
     * @param id
     * @param nombre
     * @param pais
     */
    public Provincia(Integer id, String nombre, Pais pais) {
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

    public Pais getPais() {
	return pais;
    }

    public void setPais(Pais pais) {
	this.pais = pais;
    }

    @Override
    public String toString() {
	return "Provincia [paisID=" + id + ", nombre=" + nombre + ", Pais=" + pais.toString() + "]";
    }

}