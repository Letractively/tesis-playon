package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class CuentaCliente implements Serializable {

    private static final long serialVersionUID = -5062229388596781952L;

    @SerializedName("cuentaClienteID")
    private Integer id;

    @SerializedName("nroCuenta")
    private Integer nroCuenta;

    @SerializedName("saldo")
    private float saldo;

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

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

}