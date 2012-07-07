/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase de negocio que contiene la denuncia hac�a un vehiculo
 * 
 * @author Pablo
 * 
 */

@Entity
@Table(name = "denuncia_vehiculo", catalog = "tesis_playon")
public class DenunciaVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "denunciaVehiculoID", nullable = false)
    private Integer id;

    @Column(name = "asunto", unique = false, nullable = false)
    private String asunto;

    @Column(name = "fechaAlta", unique = true, nullable = false)
    private Date fechaAlta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculoID", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playaID", nullable = false)
    private Playa playa;

    public DenunciaVehiculo(String asunto, Date fechaAlta, Vehiculo vehiculo, Playa playa) {
	this.asunto = asunto;
	this.fechaAlta = fechaAlta;
	this.vehiculo = vehiculo;
	this.playa = playa;
    }

    public String getAsunto() {
	return asunto;
    }

    public void setAsunto(String asunto) {
	this.asunto = asunto;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "DenunciaVehiculo [denunciaVehiculoID=" + id + ", asunto=" + asunto + ", fechaAlta="
		+ fechaAlta.toString() + ", Vehiculo=" + vehiculo.getPatente() + ", Playa=" + playa + "]";
    }

}
