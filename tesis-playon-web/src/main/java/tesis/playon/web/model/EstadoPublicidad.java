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
 * Clase de negocio que contiene los diferentes Estados de publicidad.
 * @author alejandro
 * @date 02/07/2012
 *
 */
@Entity
@Table(name = "EstadoPublicidad", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre_rol_usuario") })
public class EstadoPublicidad implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private String descripcion;

    /**
     * Constructor con parámetros.
     * 
     * @param id
     * @param nombre
     * @param descripcion
     */
    public EstadoPublicidad(Integer id, String nombre, String descripcion) {
	this.id = id;
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    /**
     * Devuelve el ID del objeto.
     * @return El ID del objeto.
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadoPublicidadID", nullable = false)
    public Integer getId() {
	return id;
    }

    /**
     * Devuelve el nombre del objeto.
     * @return El nombre del objeto.
     */
    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    public String getNombre() {
	return nombre;
    }

    /**
     * Setea un nuevo nombre al objeto.
     * @param nombre El nombre del objeto.
     */
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    /**
     * Devuelve la descripción del objeto.
     * @return
     */
    @Column(name = "descripcion", unique = false, nullable = true)
    public String getDescripcion() {
	return descripcion;
    }

    /**
     * Setea una nueva descripción al objeto.
     * @param descripcion La descripción del objeto.
     */
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    @Override
    public String toString() {
	return "EstadoPublicidad [estadoPublicidadID=" + id + ", nombre=" + nombre + "descripcion=" + descripcion + "]";
    }

}
