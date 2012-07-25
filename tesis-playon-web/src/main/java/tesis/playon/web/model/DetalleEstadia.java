package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author alejandro
 * @date 10/07/2012
 */
@Entity
@Table(name = "detalle_estadia", catalog = "tesis_playon")
public class DetalleEstadia implements Serializable {

    private static final long serialVersionUID = -1682927511100627927L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "detalleEstadiaID")
    private Integer id;

    @Column(name = "fechaHoraEgreso")
    private Date fechaHoraEgreso;

    @Column(name = "fechaHoraIngreso")
    private Date fechaHoraIngreso;

    @Column(name = "importeTotal")
    private float importeTotal;

    @ManyToOne
    @JoinColumn(name = "transaccionClienteID")
    private TransaccionCliente transaccionCliente;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleadoID")
    private Empleado empleado;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculoID")
    private Vehiculo vehiculo;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadiaID")
    private Estadia estadia;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarifaID")
    private Tarifa tarifa;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promocionID")
    private Promocion promocion;

    public DetalleEstadia() {
	super();
    }

    public DetalleEstadia(Date fechaHoraEgreso, Date fechaHoraIngreso, float importeTotal,
	    TransaccionCliente transaccionCliente, Empleado empleado, Vehiculo vehiculo, Estadia estadia,
	    Tarifa tarifa, Promocion promocion) {
	super();
	this.fechaHoraEgreso = fechaHoraEgreso;
	this.fechaHoraIngreso = fechaHoraIngreso;
	this.importeTotal = importeTotal;
	this.transaccionCliente = transaccionCliente;
	this.empleado = empleado;
	this.vehiculo = vehiculo;
	this.estadia = estadia;
	this.tarifa = tarifa;
	this.promocion = promocion;
    }

    public Date getFechaHoraEgreso() {
	return fechaHoraEgreso;
    }

    public void setFechaHoraEgreso(Date fechaHoraEgreso) {
	this.fechaHoraEgreso = fechaHoraEgreso;
    }

    public Date getFechaHoraIngreso() {
	return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(Date fechaHoraIngreso) {
	this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public float getImporteTotal() {
	return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
	this.importeTotal = importeTotal;
    }

    public TransaccionCliente getTransaccionCliente() {
	return transaccionCliente;
    }

    public void setTransaccionCliente(TransaccionCliente transaccionCliente) {
	this.transaccionCliente = transaccionCliente;
    }

    public Empleado getEmpleado() {
	return empleado;
    }

    public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
    }

    public Promocion getPromocion() {
	return promocion;
    }

    public void setPromocion(Promocion promocion) {
	this.promocion = promocion;
    }

    public Integer getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	DetalleEstadia otroDetalle = (DetalleEstadia) object;
	if (id != otroDetalle.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "DetalleEstadia:\t [id= " + id + ", fechaHoraIngreso= " + fechaHoraIngreso.toString()
		+ ", fechaHoraEgreso " + fechaHoraEgreso.toString() + ", importeTotal" + importeTotal + "]";
    }
}
