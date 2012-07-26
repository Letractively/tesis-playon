/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "promocion", catalog = "tesis_playon")
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "promocionID")
    private int id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "descuento")
    private float descuento;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "fechaFin")
    private Date fechaFin;

    @Column(name = "fechaInicio")
    private Date fechaInicio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "montoFijo")
    private float montoFijo;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playaID")
    private Playa playa;
    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarifaID")
    private Tarifa tarifa;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadoPromocionID")
    private EstadoPromocion estadoPromocion;

    public Promocion(String descripcion, float descuento, Date fechaAlta, Date fechaFin, Date fechaInicio,
	    String nombre, float montoFijo, Playa playa, Tarifa tarifa, EstadoPromocion estadoPromocion) {
	this.descripcion = descripcion;
	this.descuento = descuento;
	this.fechaAlta = fechaAlta;
	this.fechaFin = fechaFin;
	this.fechaInicio = fechaInicio;
	this.nombre = nombre;
	this.montoFijo = montoFijo;
	this.playa = playa;
	this.tarifa = tarifa;
	this.estadoPromocion = estadoPromocion;
    }

    public Promocion() {
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public float getDescuento() {
	return descuento;
    }

    public void setDescuento(float descuento) {
	this.descuento = descuento;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }

    public Date getFechaFin() {
	return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public float getMontoFijo() {
	return montoFijo;
    }

    public void setMontoFijo(float montoFijo) {
	this.montoFijo = montoFijo;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
    }

    public EstadoPromocion getEstadoPromocion() {
	return estadoPromocion;
    }

    public void setEstadoPromocion(EstadoPromocion estadoPromocion) {
	this.estadoPromocion = estadoPromocion;
    }

    public int getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Promocion otroPromocion = (Promocion) object;
	if (id != otroPromocion.id)
	    return false;
	if (nombre == null ? otroPromocion.nombre != null : !nombre.equals(otroPromocion.nombre))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Promocion:\t [promocionID= " + id + ", descuento= " + descuento + ", fechaAlta= " + fechaAlta
		+ ", fechaFin= " + fechaFin + ", fechaInicio= " + fechaInicio + ", nombre= " + nombre + ", montoFijo= "
		+ montoFijo + ", " + playa + ", " + tarifa + ", " + estadoPromocion + "]";
    }

}