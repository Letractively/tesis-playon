package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("posicion")
@Entity
@Table(name = "posicion")
public class Posicion implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "posicionID")
    private Integer id;

    @Column(name = "tamanioKBMax")
    private Integer tamanioKBMax;

    @Column(name = "tamanioX")
    private Integer tamanioX;

    @Column(name = "tamanioY")
    private Integer tamanioY;

    @Column(name = "ubicacion")
    private String ubicacion;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getTamanioKBMax() {
	return tamanioKBMax;
    }

    public void setTamanioKBMax(Integer tamanioKBMax) {
	this.tamanioKBMax = tamanioKBMax;
    }

    public Integer getTamanioX() {
	return tamanioX;
    }

    public void setTamanioX(Integer tamanioX) {
	this.tamanioX = tamanioX;
    }

    public Integer getTamanioY() {
	return tamanioY;
    }

    public void setTamanioY(Integer tamanioY) {
	this.tamanioY = tamanioY;
    }

    public String getUbicacion() {
	return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
    }

}