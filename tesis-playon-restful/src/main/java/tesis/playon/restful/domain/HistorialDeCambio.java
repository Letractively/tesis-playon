package tesis.playon.restful.domain;

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
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement
@XStreamAlias("historialDeCambio")
@Entity
@Table(name = "historial_de_cambio")
public class HistorialDeCambio implements Serializable {

    private static final long serialVersionUID = 740960507447874471L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "historialDeCambioID")
    private Integer id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "denunciaPlayaID")
    private DenunciaPlaya denunciaPlaya;

    @ManyToOne
    @JoinColumn(name = "denunciaVehiculoID")
    private DenunciaVehiculo denunciaVehiculoID;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public DenunciaPlaya getDenunciaPlaya() {
	return denunciaPlaya;
    }

    public void setDenunciaPlaya(DenunciaPlaya denunciaPlaya) {
	this.denunciaPlaya = denunciaPlaya;
    }

    public DenunciaVehiculo getDenunciaVehiculoID() {
	return denunciaVehiculoID;
    }

    public void setDenunciaVehiculoID(DenunciaVehiculo denunciaVehiculoID) {
	this.denunciaVehiculoID = denunciaVehiculoID;
    }

}