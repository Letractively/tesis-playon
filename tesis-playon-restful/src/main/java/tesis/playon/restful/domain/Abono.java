package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("abono")
@Entity
@Table(name = "abono")
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

    @ManyToOne
    @JoinColumn(name = "tarifaID")
    private Tarifa tarifa;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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

}