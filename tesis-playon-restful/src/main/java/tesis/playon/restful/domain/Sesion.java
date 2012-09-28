package tesis.playon.restful.domain;

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
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("sesion")
@Entity
@Table(name = "sesion")
public class Sesion implements Serializable {

    private static final long serialVersionUID = 3859234299526922177L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comentarioID")
    private Integer id;

    @Column(name = "fechaFin")
    private Date fechaFin;

    @Column(name = "fechaInicio")
    private Date fechaInicio;

    @Column(name = "idSesion")
    private String idSesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Date getFechaFin() {
	return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public String getIdSesion() {
	return idSesion;
    }

    public void setIdSesion(String idSesion) {
	this.idSesion = idSesion;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}