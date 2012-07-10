package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * Clase de negocio que contiene los diferentes Estados de publicidad.
 * 
 * @author Pablo
 * @date 04/07/2012
 * 
 */
@Entity
@Table(name = "marca_vehiculo", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre")})
public class MarcaVehiculo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "marcaVehiculoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
  
    @OneToMany
    //@OneToMany(mappedBy = "marca_vehiculo")
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "marca_vehiculo")
    private Set<ModeloVehiculo> modelos = new HashSet<ModeloVehiculo>(0);

    public MarcaVehiculo(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    public MarcaVehiculo(String nombre, String descripcion, Set<ModeloVehiculo> modelos) {
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.modelos = modelos;
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


    public Set<ModeloVehiculo> getModelos() {
        return modelos;
    }

    public void setModelos(Set<ModeloVehiculo> modelos) {
        this.modelos = modelos;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
	return "MarcaVehiculo:\t [marcaVehiculoID=" + id + ", nombre= " + nombre + ", descripcion= " + descripcion + "]";
    }    
}
