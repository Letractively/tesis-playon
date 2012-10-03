package tesis.playon.restful.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "usuario_sistema")
public class UsuarioSistema implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuarioSistemaID")
    private int id;

    @ManyToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolUsuarioID")
    private RolUsuario rolUsuario;

    @OneToOne
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public RolUsuario getRolUsuario() {
	return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
	this.rolUsuario = rolUsuario;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}