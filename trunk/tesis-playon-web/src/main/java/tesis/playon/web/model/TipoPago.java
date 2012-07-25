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
 * Tipos de pago para las transacciones de los clientes y de las playas.
 * @author alejandro
 * @date 07/07/2012
 */
@Entity
@Table(name = "tipo_pago", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class TipoPago implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tipoPagoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;

    /**
     * Constructor por defecto.
     */
    public TipoPago() {
	super();
    }

    /**
     * Constructor con parámetros.
     * @param nombre El nombre del Tipo de Pago.
     * @param descripcion La descripción del Tipo de Pago.
     */
    public TipoPago(String nombre, String descripcion) {
	super();
	this.nombre = nombre;
	this.descripcion = descripcion;
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

	TipoPago otroTipoPago = (TipoPago) object;
	if (id != otroTipoPago.id)
	    return false;
	if (nombre == null ? otroTipoPago.nombre != null : !nombre.equals(otroTipoPago.nombre))
	    return false;

	return true;
    }
    
    
    
    public String toString(){
	return "TipoPago:\t [TipoPagoID= " + id + ", nombre= " + nombre + ", descripcion=" + descripcion + "]";
    }
}
