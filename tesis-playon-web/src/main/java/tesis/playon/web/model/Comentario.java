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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Clase de negocio que contiene los comentarios sobre una playa
 * 
 * @author garribere
 * 
 */
@Entity
@Table(name = "comentario", catalog = "tesis_playon")
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comentarioID")
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "calificacion")
    private int calificacion;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "habilitado")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean habilitado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playaID")
    private Playa playa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    @Column(name = "patente")
    private String patente;

    public Comentario(Cliente cliente, String comentario, Playa playa, boolean habilitado, int calificacion, Date fecha) {
	this.fecha = fecha;
	this.cliente = cliente;
	this.comentario = comentario;
	this.playa = playa;
	this.habilitado = habilitado;
	this.fecha = fecha;
    }

    public Comentario() {
	// TODO Auto-generated constructor stub
    }

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

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public boolean isHabilitado() {
	return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
	this.habilitado = habilitado;
    }

    public int getCalificacion() {
	return calificacion;
    }

    public void setCalificacion(int calificacion) {
	this.calificacion = calificacion;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Comentario otroComent = (Comentario) object;
	if (id != otroComent.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Comentario:\t [comentarioID= " + id + ", fecha= " + fecha.toString() + ", comentario= " + comentario
		+ ", habilitado= " + habilitado + "]";
    }

}
