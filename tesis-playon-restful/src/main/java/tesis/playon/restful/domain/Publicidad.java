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

@XmlRootElement
@Entity
@Table(name = "publicidad")
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

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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

}