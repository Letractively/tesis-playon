package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @SerializedName("clienteID")
    private Integer id;

    @SerializedName("nroCliente")
    private Integer nroCliente;

    @SerializedName("domicilio")
    private String domicilio;

    @SerializedName("telefono")
    private String telefono;

    private Barrio barrio;

    private Usuario usuario;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getNroCliente() {
	return nroCliente;
    }

    public void setNroCliente(Integer nroCliente) {
	this.nroCliente = nroCliente;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}