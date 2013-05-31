package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Playa implements Serializable {

    private static final long serialVersionUID = -4573436968149297463L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("domicilio")
    private String domicilio;

    @SerializedName("telefono")
    private String telefono;

    private Barrio barrio;

    @SerializedName("email")
    private String email;

    @SerializedName("cuit")
    private String cuit;

    @SerializedName("longitud")
    private Double longitud;

    @SerializedName("latitud")
    private Double latitud;

    private EstadoPlaya estado;

    @SerializedName("disponibilidad")
    private Integer disponibilidad;

    @SerializedName("nombreComercial")
    private String nombreComercial;

    @SerializedName("razonSocial")
    private String razonSocial;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getCuit() {
	return cuit;
    }

    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    public Double getLongitud() {
	return longitud;
    }

    public void setLongitud(Double longitud) {
	this.longitud = longitud;
    }

    public Double getLatitud() {
	return latitud;
    }

    public void setLatitud(Double latitud) {
	this.latitud = latitud;
    }

    public EstadoPlaya getEstado() {
	return estado;
    }

    public void setEstado(EstadoPlaya estado) {
	this.estado = estado;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public String getNombreComercial() {
	return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
	this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public double getDistanceFrom(double latitud, double longitud) {

	double earthRadius = 6371;

	double dLat = Math.toRadians(this.latitud - latitud);

	double dLng = Math.toRadians(this.longitud - longitud);

	double sindLat = Math.sin(dLat / 2);

	double sindLng = Math.sin(dLng / 2);

	double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(this.latitud) * Math.cos(latitud);

	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	double dist = earthRadius * c;

	return dist * 10;

    }

}