package tesis.playon.web.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 
 * @author alejandro
 * @date 07/07/2012
 */
@Entity
@Table(name = "abono", catalog = "tesis_playon")
public class Abono implements Serializable {

    private static final long serialVersionUID = 7220407400717111379L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "abonoID")
    private Integer id;

    @Column(name = "fechaVigenciaDesde")
    private Date fechaVigenciaDesde;

    @Column(name = "fechaVegenciaHasta")
    private Date fechaVigenciaHasta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarifaID")
    private Tarifa tarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playaID")
    private Playa playa;

    /**
     * Constructor por defecto.
     */
    public Abono() {
	super();
    }

    /**
     * Constructor con parámetros.
     * @param fechaVigenciaDesde
     * @param fechaVigenciaHasta
     * @param tarifa
     * @param cliente
     * @param playa
     */
    public Abono(Date fechaVigenciaDesde, Date fechaVigenciaHasta, Tarifa tarifa, Cliente cliente, Playa playa) {
	super();
	this.fechaVigenciaDesde = fechaVigenciaDesde;
	this.fechaVigenciaHasta = fechaVigenciaHasta;
	this.tarifa = tarifa;
	this.cliente = cliente;
	this.playa = playa;
    }

    public Date getFechaVigenciaDesde() {
	return fechaVigenciaDesde;
    }

    public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
	this.fechaVigenciaDesde = fechaVigenciaDesde;
    }

    public Date getFechaVigenciaHasta() {
	return fechaVigenciaHasta;
    }

    public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
	this.fechaVigenciaHasta = fechaVigenciaHasta;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
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

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Abono [abonoID=" + id + ", fechaVigenciaDesde=" + fechaVigenciaDesde.toString()
		+ ", fechaVigenciaHasta=" + fechaVigenciaHasta.toString() + ", cliente=" + cliente.toString()
		+ ", playa=" + playa.toString() + "]";
    }
}
