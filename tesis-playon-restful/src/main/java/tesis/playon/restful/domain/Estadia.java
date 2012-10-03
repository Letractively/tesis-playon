package tesis.playon.restful.domain;

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

@XmlRootElement
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

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

}