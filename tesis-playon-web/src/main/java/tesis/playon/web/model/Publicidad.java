package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que contiene las publicidades.
 * 
 * @author alejandro
 * @date 07/07/2012
 */
@Entity
@Table(name = "publicidad", catalog = "tesis_playon")
public class Publicidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "publicidadID")
    private Integer id;

    @Column(name = "urlImagen")
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "posicionID")
    private Posicion posicion;

    @ManyToOne
    @JoinColumn(name = "estadoPublicidadID")
    private EstadoPublicidad estado;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    /**
     * Constructor por defecto.
     */
    public Publicidad() {
	super();
    }

    /**
     * Constructor con parámetros
     * 
     * @param urlImagen
     *            El URL de la imagen de la publicidad.
     * @param posicion
     *            La posición de la publicidad en el sitio.
     * @param estado
     *            El estado actual de la publicidad.
     * @param playa
     *            La playa a la que pertenece la publicidad.
     */
    public Publicidad(String urlImagen, Posicion posicion, EstadoPublicidad estado, Playa playa) {
	super();
	this.urlImagen = urlImagen;
	this.posicion = posicion;
	this.estado = estado;
	this.playa = playa;
    }

    public String getUrlImagen() {
	return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
	this.urlImagen = urlImagen;
    }

    public Posicion getPosicion() {
	return posicion;
    }

    public void setPosicion(Posicion posicion) {
	this.posicion = posicion;
    }

    public EstadoPublicidad getEstado() {
	return estado;
    }

    public void setEstado(EstadoPublicidad estado) {
	this.estado = estado;
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

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Publicidad otroPublicidad = (Publicidad) object;
	if (id != otroPublicidad.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Publicidad:\t [publicidadID=" + id + ", urlImagen=" + urlImagen + ", " + posicion.toString() + ", "
		+ estado.toString() + "]";
    }
}