package tesis.playon.restful.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "marca_vehiculo")
@Entity
@Table(name = "marca_vehiculo")
public class MarcaVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "marcaVehiculoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public MarcaVehiculo(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    public MarcaVehiculo() {
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

    public Integer getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	MarcaVehiculo otroMarca = (MarcaVehiculo) object;
	if (id != otroMarca.id)
	    return false;
	if (nombre == null ? otroMarca.nombre != null : !nombre.equals(otroMarca.nombre))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "MarcaVehiculo:\t [marcaVehiculoID=" + id + ", nombre= " + nombre + ", descripcion= " + descripcion
		+ "]";
    }

    public void setId(Integer id) {
	this.id = id;

    }
}
