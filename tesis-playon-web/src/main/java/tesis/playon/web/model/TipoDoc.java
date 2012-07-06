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
 * Clase de negocio que contiene los diferentes tipos documentos
 * 
 * @author garribere
 * @date 05/07/2012
 * 
 */
@Entity
@Table(name = "tipo_doc", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class TipoDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tipoDocID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

        
    public TipoDoc() {
    }

    public TipoDoc(String nombre, String descripcion) {
	this.nombre = nombre;
    }

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

       @Override
    public String toString() {
	return "TipoDoc [TipoDocID=" + id + ", nombre=" + nombre + "]";
    }

}
