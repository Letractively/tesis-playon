package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "usuario", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "usuario") })
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

    @Column(name = "nroDoc", unique = true)
    private int nroDoc;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "tipoDocID")
    private TipoDoc tipoDoc;

    @Column(name = "usuario", unique = true)
    private String nombreUser;

    public Usuario() {
    }

    public Usuario(String apellido, String nombre, String nombreUser, String passwd, String email, TipoDoc tipoDoc,
	    int nroDoc) {
	this.apellido = apellido;
	this.nombre = nombre;
	this.email = email;
	this.nroDoc = nroDoc;
	this.tipoDoc = tipoDoc;
	this.nombreUser = nombreUser;
	this.password = passwd;
    }

    public void setID(int id) {
	this.id = id;

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
    

    public String getNombreUser() {
	return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
	this.nombreUser = nombreUser;
    }

    public Integer getId() {
	return id;
    }
    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Usuario otroUsuario = (Usuario) object;
	if (id != otroUsuario.id)
	    return false;
	if (nombreUser == null ? otroUsuario.nombreUser != null : !nombreUser.equals(otroUsuario.nombreUser))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Usuario:\t [usuarioID= " + id + ", nombre= " + nombre + ", apellido=" + apellido + ", " + tipoDoc
		+ ", Nro Documento= " + nroDoc + ", user= " + nombreUser + "]";
    }
}