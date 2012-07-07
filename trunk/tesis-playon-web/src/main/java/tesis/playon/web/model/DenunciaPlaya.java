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
 * Clase de negocio que contiene denuncias de los clientes a las playas
 * 
 * @author garribere
 * 
 */

@Entity
@Table(name = "denuncia_playa", catalog = "tesis_playon")
public class DenunciaPlaya implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "denunciaPlayaID", nullable = false)
    private Integer id;

    @Column(name = "asunto", unique = false, nullable = false)
    private String asunto;

    @Column(name = "fechaAlta", unique = true, nullable = false)
    private Date fechaAlta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playaID", nullable = false)
    private Playa playa;

    public DenunciaPlaya(String asunto, Date fechaAlta, Playa playa, Cliente cliente) {
	this.asunto = asunto;
	this.fechaAlta = fechaAlta;
	this.playa = playa;
	this.cliente = cliente;
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

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    @Override
    public String toString() {
	return "DenunciaPlaya [denunciaPlayaID=" + id + ", asunto=" + asunto + ", fechaAlta=" + fechaAlta
		+ ", Cliente=" + cliente.getNroCliente() + ", Playa=" + playa + "]";
    }

}