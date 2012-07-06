package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Comentario [comentarioID=" + id + ", fecha: =" + fecha + ", comentairo: = " + comentario
		+ ", habilitado=" + habilitado + "]";
    }

}
