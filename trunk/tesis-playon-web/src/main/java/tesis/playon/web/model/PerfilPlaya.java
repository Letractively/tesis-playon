package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase de negocio que contiene los comentarios sobre una playa
 * 
 * @author gmorales
 * 
 */
@Entity
@Table(name = "perfil_playa", catalog = "tesis_playon")
public class PerfilPlaya implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "perfilPlayaID")
    private Integer id;

    @Column(name = "nombreComercial")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;
    
    @Lob
    @Column(name = "fotoPerfil", columnDefinition="mediumblob")
    private byte[] fotoPerfil;
    
    @Column(name = "nombreFoto")
    private String nombreFoto;
    
    @Column(name = "cantidadVotantes")
    private Integer cantidadVotantes;
    
    @Column(name = "totalCalificaciones")
    private Integer totalCalificaciones;

    public PerfilPlaya() {
    }

    public PerfilPlaya(String nombre, String descripcion, Playa playa) {
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.playa = playa;
    }
    
    public PerfilPlaya(byte[] fotoPerfil) {
	this.fotoPerfil = fotoPerfil;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getCantidadVotantes() {
        return cantidadVotantes;
    }

    public void setCantidadVotantes(Integer cantidadVotantes) {
        this.cantidadVotantes = cantidadVotantes;
    }

    public Integer getTotalCalificaciones() {
        return totalCalificaciones;
    }

    public void setTotalCalificaciones(Integer totalCalificaciones) {
        this.totalCalificaciones = totalCalificaciones;
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    @Override
    public String toString() {
	return "PerfilPlaya:\t [perfilPlayaID=" + id + ", nombreComercial=" + nombre + ", descripcion=" + descripcion
		+ "]";
    }

}
