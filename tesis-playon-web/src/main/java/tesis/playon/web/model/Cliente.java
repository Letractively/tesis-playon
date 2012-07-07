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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Alejandro
 * @date 06/07/2012
 */

@Entity
@Table(name = "cliente", catalog = "tesis_playon")
public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "clienteID")
    private Integer id;
    
    @Column(name = "nroCliente")
    private Integer nroCliente;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "telefono")
    private String telefono;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barrioID")
    private Barrio barrioID;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuentaClienteID")
    private CuentaCliente cuentaClienteID;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioID")
    private Usuario usuarioID;

    public Integer getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(Integer nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Barrio getBarrioID() {
        return barrioID;
    }

    public void setBarrioID(Barrio barrioID) {
        this.barrioID = barrioID;
    }

    public CuentaCliente getCuentaClienteID() {
        return cuentaClienteID;
    }

    public void setCuentaClienteID(CuentaCliente cuentaClienteID) {
        this.cuentaClienteID = cuentaClienteID;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Integer getId() {
        return id;
    }
    
    @Override
    public String toString() {
	return "Cliente [clienteID=" + id + ", nroCliente=" + nroCliente + ", domicilio=" + domicilio
		+ "]";
    }
}
