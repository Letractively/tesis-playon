package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import tesis.playon.restful.util.TimestampAdapter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("cuentaPlaya")
@Entity
@Table(name = "cuenta_playa")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuentaPlaya implements Serializable {

    private static final long serialVersionUID = -7370950550724631370L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cuentaPlayaID")
    private Integer id;

    @Column(name = "nroCuenta")
    private Integer nroCuenta;

    @Column(name = "fechaCreacion")
    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp fechaCreacion;

    @Column(name = "saldo")
    private float saldo;

    @OneToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getNroCuenta() {
	return nroCuenta;
    }

    public void setNroCuenta(Integer nroCuenta) {
	this.nroCuenta = nroCuenta;
    }

    public Timestamp getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

    public float getSaldo() {
	return saldo;
    }

    public void setSaldo(float saldo) {
	this.saldo = saldo;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

}