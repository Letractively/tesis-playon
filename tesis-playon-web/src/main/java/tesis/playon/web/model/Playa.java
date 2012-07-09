/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Alejandro
 * @date 08/07/2012
 */
@Entity
@Table(name = "playa", catalog = "tesis_playon")
public class Playa implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "playaID")
    private Integer id;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "disponibilidad")
    private Integer disponibilidad;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "nombreComercial")
    private String nombreComercial;

    @Column(name = "razonSocial")
    private String razonSocial;

    @ManyToOne
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barrioID")
    private Barrio barrio;

    @ManyToOne
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadoID", nullable= true)
    private EstadoPlaya estado;

    @OneToOne
    //@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadiaID")
    private Estadia estadia;

    public Playa() {
	super();
    }

    public Playa(String cuit, Integer disponibilidad, String domicilio, String nombreComercial, String razonSocial,
	    Barrio barrio, EstadoPlaya estado, Estadia estadia) {
	this.cuit = cuit;
	this.disponibilidad = disponibilidad;
	this.domicilio = domicilio;
	this.nombreComercial = nombreComercial;
	this.razonSocial = razonSocial;
	this.barrio = barrio;
	this.estado = estado;
	this.estadia = estadia;
    }
    
    public Playa(String nombreComercial, EstadoPlaya estado) {
	this.nombreComercial = nombreComercial;
	this.estado = estado;
    }

    public String getCuit() {
	return cuit;
    }

    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getNombreComercial() {
	return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
	this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public EstadoPlaya getEstado() {
	return estado;
    }

    public void setEstado(EstadoPlaya estado) {
	this.estado = estado;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Playa:\t [playaID=" + id + ", cuit=" + cuit + ", nombreComercial=" + nombreComercial + ", razonSocial="
		+ razonSocial + ", domicilio=" + domicilio + "]";
    }
}
