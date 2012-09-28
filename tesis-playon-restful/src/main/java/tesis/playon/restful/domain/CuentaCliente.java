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
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("cuentaVehiculo")
@Entity
@Table(name = "cuenta_cliente")
public class CuentaCliente implements Serializable {

    private static final long serialVersionUID = -5062229388596781952L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cuentaClienteID")
    private Integer id;

    @Column(name = "nroCuenta")
    private Integer nroCuenta;

    @Column(name = "saldo")
    private float saldo;

    @Column(name = "fechaCreacion")
    private Timestamp fechaCreacion;

    @OneToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

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

    public float getSaldo() {
	return saldo;
    }

    public void setSaldo(float saldo) {
	this.saldo = saldo;
    }

    public Timestamp getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

}