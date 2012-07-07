/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Pablo
 *
 */
@Entity
@Table(name = "modelo_vehiculo", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class ModeloVehiculo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "modeloVehiculoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marcaVehiculoID")
    private MarcaVehiculo marcaVehiculo;

    
    public ModeloVehiculo(String nombre, String descripcion, MarcaVehiculo marcaVehiculo) {
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.marcaVehiculo = marcaVehiculo;
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


    public MarcaVehiculo getMarcaVehiculo() {
        return marcaVehiculo;
    }


    public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }


    public Integer getId() {
        return id;
    }


    @Override
    public String toString() {
	return "ModeloVehiculo [modeloVehiculoID=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", marcaVehiculo="
		+ marcaVehiculo.getNombre() + "]";
    }
    
    
    
    
}