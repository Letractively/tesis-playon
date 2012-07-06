/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
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
 * @author Pablo
 *
 */
@Entity
@Table(name = "vehiculo", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "codigoBarra"), @UniqueConstraint(columnNames = "patente")})
public class Vehiculo implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "vehiculoID")
    private Integer id;
    
    @Column(name = "año")
    private int año;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaID")
    private CategoriaVehiculo categoriaVehiculo;
    
    @Column(name = "codigoBarra")
    private String codigoBarra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colorID")
    private ColorVehiculo colorVehiculo;
    
    @Column(name = "habilitado")
    private boolean habilitado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modeloVehiculoID")
    private ModeloVehiculo modeloVehiculo;
    
    @Column(name = "patente")
    private String patente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    public Vehiculo(int año, CategoriaVehiculo categoriaVehiculo, String codigoBarra, ColorVehiculo colorVehiculo,
	    boolean habilitado, ModeloVehiculo modeloVehiculo, String patente, Cliente cliente) {
	this.año = año;
	this.categoriaVehiculo = categoriaVehiculo;
	this.codigoBarra = codigoBarra;
	this.colorVehiculo = colorVehiculo;
	this.habilitado = habilitado;
	this.modeloVehiculo = modeloVehiculo;
	this.patente = patente;
	this.cliente = cliente;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public CategoriaVehiculo getCategoriaVehiculo() {
        return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
        this.categoriaVehiculo = categoriaVehiculo;
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

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
	return "Vehiculo [vehiculoID=" + id + ", año=" + año + ", codigoBarra=" + codigoBarra + ", habilitado=" + habilitado
		+ ", patente=" + patente + "]";
    }
    
    
}
