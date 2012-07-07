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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los usuarios.
 * 
 * @author garribere
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "usuario", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "usuario"),
	@UniqueConstraint(columnNames = "nroDocumento"), @UniqueConstraint(columnNames = "usuarioID") })
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuarioID")
    private Integer id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "nroDoc")
    private int nroDoc;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoDocID")
    private TipoDoc tipoDoc;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String password, TipoDoc tipoDoc, int nroDoc) {
	this.nombre = nombre;
	this.apellido = apellido;
	this.email = email;
	this.password = password;
	this.tipoDoc = tipoDoc;
	this.nroDoc = nroDoc;

    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public int getNroDoc() {
	return nroDoc;
    }

    public void setNroDoc(int nroDoc) {
	this.nroDoc = nroDoc;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public TipoDoc getTipoDoc() {
	return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
	this.tipoDoc = tipoDoc;
    }

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Usuario [usuarioID=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
    }

}