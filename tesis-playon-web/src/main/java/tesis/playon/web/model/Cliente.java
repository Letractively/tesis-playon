package tesis.playon.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente", catalog = "tesis_playon")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 4725165248854057805L;

    private Long dni;

    private String nombre;

    private String apellido;

    private Double saldo;

    public Cliente(Long dni, String nombre, String apellido, Double saldo) {
	this.dni = dni;
	this.nombre = nombre;
	this.apellido = apellido;
	this.saldo = saldo;
    }

    @Id
    @Column(name = "dni", nullable = false)
    public Long getDni() {
	return dni;
    }

    public void setDni(Long dni) {
	this.dni = dni;
    }

    @Column(name = "nombre", nullable = false, length = 20)
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Column(name = "apellido", nullable = false, length = 20)
    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    @Column(name = "saldo", nullable = false)
    public Double getSaldo() {
	return saldo;
    }

    public void setSaldo(Double saldo) {
	this.saldo = saldo;
    }

    @Override
    public String toString() {
	return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", saldo=" + saldo + "]";
    }

}
