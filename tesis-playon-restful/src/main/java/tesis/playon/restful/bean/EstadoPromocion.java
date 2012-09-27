package tesis.playon.restful.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "estado_promocion")
@Entity
@Table(name = "estado_promocion")
public class EstadoPromocion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadoPromocionID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public EstadoPromocion() {
    }

    public EstadoPromocion(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
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

    public String getDescripcion() {
	return descripcion;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	EstadoPromocion otroEstado = (EstadoPromocion) object;
	if (id != otroEstado.id)
	    return false;
	if (nombre == null ? otroEstado.nombre != null : !nombre.equals(otroEstado.nombre))
	    return false;

	return true;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    @Override
    public String toString() {
	return "EstadoPromocion:\t [EstadoPromocionID= " + id + ", nombre= " + nombre + ", descripcion= " + descripcion
		+ "]";
    }
}