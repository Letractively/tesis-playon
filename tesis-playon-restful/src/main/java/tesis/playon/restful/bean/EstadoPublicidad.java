package tesis.playon.restful.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "estado_publicidad")
@Entity
@Table(name = "estado_publicidad")
public class EstadoPublicidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadoPublicidadID", nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", unique = false, nullable = true)
    private String descripcion;

    public EstadoPublicidad() {
	super();
    }

    public EstadoPublicidad(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
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

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	EstadoPublicidad otroEstado = (EstadoPublicidad) object;
	if (id != otroEstado.id)
	    return false;
	if (nombre == null ? otroEstado.nombre != null : !nombre.equals(otroEstado.nombre))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "EstadoPublicidad:\t [estadoPublicidadID= " + id + "nombre= " + nombre + "descripcion= " + descripcion
		+ "]";
    }

}
