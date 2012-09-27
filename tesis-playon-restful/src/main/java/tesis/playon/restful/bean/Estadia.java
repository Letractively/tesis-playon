package tesis.playon.restful.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "estadia")
@Entity
@Table(name = "estadia")
public class Estadia implements Serializable {

    private static final long serialVersionUID = -223607510306361286L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadiaID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    public Estadia() {
	super();
    }

    public Estadia(Playa playa) {
	super();
	this.playa = playa;
    }

    public Integer getId() {
	return id;
    }

    public Playa getPlaya() {
	return playa;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Estadia otroEstadia = (Estadia) object;
	if (id != otroEstadia.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Estadia:\t [estadiaID= " + id + ", playa= " + playa.getNombreComercial() + "]";
    }
}
