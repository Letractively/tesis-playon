package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los diferentes provincias.
 * 
 * @author garribere
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "cuenta_playa", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nroCuenta") })
public class CuentaPlaya implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cuentaPlayaID")
    private Integer id;

    @Column(name = "numero")
    private int nroCuenta;

    @Column(name = "fechaCreacion")
    private Date fechaCreacion;

    @Column(name = "saldo")
    private float saldo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playaID")
    private Playa playa;

    public CuentaPlaya() {
    }

    public CuentaPlaya(int nroCuenta) {
	this.nroCuenta = nroCuenta;
    }

    public int getNroCuenta() {
	return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
	this.nroCuenta = nroCuenta;
    }

    public Date getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
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

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Cuenta Playa [cuentaPlayaID=" + id + ", nroCuenta=" + nroCuenta + "]";
    }

}