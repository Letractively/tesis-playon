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

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("vehiculo")
@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "vehiculoID")
    private Integer id;

    @Column(name = "anio")
    private int anio;

    @Column(name = "codigoBarra")
    private String codigoBarra;

    @ManyToOne
    @JoinColumn(name = "colorVehiculoID")
    private ColorVehiculo colorVehiculo;

    @Column(name = "habilitado")
    private boolean habilitado;

    @ManyToOne
    @JoinColumn(name = "modeloVehiculoID")
    private ModeloVehiculo modeloVehiculo;

    @Column(name = "patente")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public int getAnio() {
	return anio;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public String getCodigoBarra() {
	return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
	this.codigoBarra = codigoBarra;
    }

    public ColorVehiculo getColorVehiculo() {
	return colorVehiculo;
    }

    public void setColorVehiculo(ColorVehiculo colorVehiculo) {
	this.colorVehiculo = colorVehiculo;
    }

    public boolean isHabilitado() {
	return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
	this.habilitado = habilitado;
    }

    public ModeloVehiculo getModeloVehiculo() {
	return modeloVehiculo;
    }

    public void setModeloVehiculo(ModeloVehiculo modeloVehiculo) {
	this.modeloVehiculo = modeloVehiculo;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

}