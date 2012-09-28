package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("liquidacion")
@Entity
@Table(name = "liquidacion")
public class Liquidacion implements Serializable {

    private static final long serialVersionUID = 298942841995116227L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "liquidacionID")
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "fechaDesde")
    private Date fechaDesde;

    @Column(name = "fechaHasta")
    private Date fechaHasta;

    @Column(name = "importeTotal")
    private float importeTotal;

    @OneToOne
    @JoinColumn(name = "estadiaID")
    private Estadia estadia;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public Date getFechaDesde() {
	return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
	return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    public float getImporteTotal() {
	return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
	this.importeTotal = importeTotal;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

}