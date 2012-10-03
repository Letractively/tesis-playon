package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "color_vehiculo")
public class ColorVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "colorVehiculoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valorHexadecimal")
    private String valorHexadecimal;

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

    public String getValorHexadecimal() {
	return valorHexadecimal;
    }

    public void setValorHexadecimal(String valorHexadecimal) {
	this.valorHexadecimal = valorHexadecimal;
    }

}
