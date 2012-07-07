/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author garribere
 * @date 06/07/2012
 */

@Entity
@Table(name = "empleado", catalog = "tesis_playon")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "empleadoID")
    private Integer id;

    @Column(name = "legajo")
    private Integer legajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargoEmpleadoID")
    private CargoEmpleado cargoEmpleado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    public Empleado() {

    }

    public Empleado(int legajo) {
	this.legajo = legajo;
    }

    public Integer getLegajo() {
	return legajo;
    }

    public void setLegajo(Integer legajo) {
	this.legajo = legajo;
    }

    public CargoEmpleado getCadgoEmpleado() {
	return cargoEmpleado;
    }

    public void setCadgoEmpleado(CargoEmpleado cadgoEmpleado) {
	this.cargoEmpleado = cadgoEmpleado;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Empleado [EmpleadoID=" + id + ", legajo=" + legajo + "]";
    }
}
