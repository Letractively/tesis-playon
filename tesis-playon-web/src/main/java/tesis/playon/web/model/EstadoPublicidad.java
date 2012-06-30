package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EstadoPublicidad", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre_rol_usuario") })
public class EstadoPublicidad implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nombre;
    private String descripcion;
    
    

    public EstadoPublicidad() {
    }
    
    /**
     * Constructor con parámetros.
     * @param id
     * @param nombre
     * @param descripcion
     */
    public EstadoPublicidad(Integer id, String nombre, String descripcion) {
	this.id = id;
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadoPublicidadID", nullable = false)
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Column(name = "descripcion", unique = false, nullable = true)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
	return "EstadoPublicidad [estadoPublicidadID=" + id + ", nombre=" + nombre +  "descripcion="+ descripcion +"]";
    }

}
