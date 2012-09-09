package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private Timestamp fecha;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "habilitado")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean habilitado;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    @ManyToOne
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    public Comentario(Usuario usuario, String comentario, Playa playa, Boolean habilitado) {
	this.usuario = usuario;
	this.comentario = comentario;
	this.playa = playa;
	this.habilitado = habilitado;
	this.fecha = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public Comentario() {
	// TODO Auto-generated constructor stub
	this.fecha = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Timestamp getFecha() {
	return fecha;
    }

    public void setFecha(Timestamp fecha) {
	this.fecha = fecha;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
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

    public Boolean isHabilitado() {
	return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
	this.habilitado = habilitado;
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
