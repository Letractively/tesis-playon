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
import javax.persistence.Table;

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "foto", catalog = "tesis_playon")
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fotoID")
    private int id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "link")
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfilPlayaID")
    private PerfilPlaya perfilPlaya;

    public Foto(String descripcion, String link, PerfilPlaya perfilPlaya) {
	this.descripcion = descripcion;
	this.link = link;
	this.perfilPlaya = perfilPlaya;
    }

    public Foto() {
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getLink() {
	return link;
    }

    public void setLink(String link) {
	this.link = link;
    }

    public PerfilPlaya getPerfilPlaya() {
	return perfilPlaya;
    }

    public void setPerfilPlaya(PerfilPlaya perfilPlaya) {
	this.perfilPlaya = perfilPlaya;
    }

    public int getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Foto otroFoto = (Foto) object;
	if (id != otroFoto.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Foto:\t [fotoID= " + id + ", descripcion= " + descripcion + ", link= " + link + ", perfilPlayaID= "
		+ perfilPlaya.getId() + "]";
    }
}
