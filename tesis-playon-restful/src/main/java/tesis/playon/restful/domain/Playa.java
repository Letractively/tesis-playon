package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@XmlRootElement
@NamedNativeQueries({ @NamedNativeQuery(name = "callPlayasStoreProcedure", query = "CALL busquedaplaya(:platitud,:plongitud, :pdistancia)", resultClass = Playa.class) })
@Entity
@Table(name = "playa")
public class Playa implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "playaID")
    private Integer id;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "disponibilidad")
    private Integer disponibilidad;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "nombreComercial")
    private String nombreComercial;

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "barrioID")
    private Barrio barrio;

    @ManyToOne
    @JoinColumn(name = "estadoPlayaID")
    private EstadoPlaya estado;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCuit() {
	return cuit;
    }

    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
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

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
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

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public EstadoPlaya getEstado() {
	return estado;
    }

    public void setEstado(EstadoPlaya estado) {
	this.estado = estado;
    }

}