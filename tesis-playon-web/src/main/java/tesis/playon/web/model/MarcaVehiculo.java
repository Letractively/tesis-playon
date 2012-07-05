package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "marca_vehiculo", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre"), @UniqueConstraint(columnNames = "marcaVehiculoID") })
public class MarcaVehiculo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "marcaVehiculoID", nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", unique = false, nullable = true)
    private String descripcion;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "marca_vehiculo")
    private Set<MarcaVehiculo> marcasVehiculo = new HashSet<MarcaVehiculo>(0);
    
    /**
     * Constructor con parámetros.
     * 
     * @param id
     * @param nombre
     * @param descripcion
     */
    public MarcaVehiculo(Integer id, String nombre, String descripcion) {
	this.id = id;
	this.nombre = nombre;
	this.descripcion = descripcion;
    }
       
    public MarcaVehiculo(String nombre, String descripcion, Set<MarcaVehiculo> marcasVehiculo) {
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.marcasVehiculo = marcasVehiculo;
    }



    /**
     * Devuelve el ID del objeto.
     * 
     * @return El ID del objeto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Devuelve el nombre del objeto.
     * 
     * @return El nombre del objeto.
     */
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

    /**
     * Devuelve la descripción del objeto.
     * 
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setea una nueva descripción al objeto.
     * 
     * @param descripcion
     *            La descripción del objeto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Set<MarcaVehiculo> getMarcasVehiculo() {
        return marcasVehiculo;
    }

    public void setMarcasVehiculo(Set<MarcaVehiculo> marcasVehiculo) {
        this.marcasVehiculo = marcasVehiculo;
    }

    @Override
    public String toString() {
	return "MarcaVehiculo [marcaVehiculoID=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

}
