package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "foto_usuario")
public class FotoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fotoUsuarioID")
    private int id;

    @Lob
    @Column(name = "fotoUsuario", columnDefinition = "mediumblob")
    private byte[] fotoUsuario;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public byte[] getFotoUsuario() {
	return fotoUsuario;
    }

    public void setFotoUsuario(byte[] fotoUsuario) {
	this.fotoUsuario = fotoUsuario;
    }

}