/**
 * 
 */
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
 * @author Pablo
 *
 */
@Entity
@Table(name = "color_vehiculo", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class ColorVehiculo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "colorVehiculoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public ColorVehiculo(String nombre) {
	this.nombre = nombre;
    }
    
    public ColorVehiculo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
	return "ColorVehiculo:\t[colorVehiculoID= " + id + ", nombre= " + nombre + "]";
    }

}
