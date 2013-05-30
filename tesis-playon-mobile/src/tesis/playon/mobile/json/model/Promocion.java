package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("promocionID")
    private int id;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("descuento")
    private float descuento;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("montoFijo")
    private float montoFijo;

    private Playa playa;

    private Tarifa tarifa;

    private EstadoPromocion estadoPromocion;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public float getDescuento() {
	return descuento;
    }

    public void setDescuento(float descuento) {
	this.descuento = descuento;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public float getMontoFijo() {
	return montoFijo;
    }

    public void setMontoFijo(float montoFijo) {
	this.montoFijo = montoFijo;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
    }

    public EstadoPromocion getEstadoPromocion() {
	return estadoPromocion;
    }

    public void setEstadoPromocion(EstadoPromocion estadoPromocion) {
	this.estadoPromocion = estadoPromocion;
    }

}