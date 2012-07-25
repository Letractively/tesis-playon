/**
 * 
 */
package tesis.playon.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Embeddable;
import javax.persistence.Column;

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "favorito", catalog = "tesis_playon")
public class Favorito implements Serializable {

    private static final long serialVersionUID = 1L;

    private FavoritoPK primaryKey;

    public Favorito() {
    }

    public Favorito(Playa playa, Cliente cliente) {
	primaryKey = new FavoritoPK(playa, cliente);
    }

    @Id
    public FavoritoPK getPrimaryKey() {
	return primaryKey;
    }

    public void setPrimaryKey(FavoritoPK primaryKey) {
	this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
	return "Favorito:\t [Favorito= " + primaryKey + "]";
    }

    @Embeddable()
    class FavoritoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "playaID")
	private Playa playa;

	@Column(name = "clienteID")
	private Cliente cliente;

	public FavoritoPK(Playa playa, Cliente cliente) {
	    this.playa = playa;
	    this.cliente = cliente;
	}

	public FavoritoPK() {
	}

	public Playa getPlaya() {
	    return playa;
	}

	public void setPlaya(Playa playa) {
	    this.playa = playa;
	}

	public Cliente getCliente() {
	    return cliente;
	}

	public void setCliente(Cliente cliente) {
	    this.cliente = cliente;
	}

	public boolean equals(Object object) {
	    if (object == this)
		return true;
	    if (object == null || getClass() != object.getClass())
		return false;

	    Favorito otroFavorito = (Favorito) object;
	    if (primaryKey != otroFavorito.primaryKey)
		return false;
	    return true;
	}

	@Override
	public String toString() {
	    return "FavoritoPK:\t [nombre playa= " + playa.getNombreComercial() + ", nroCliente= "
		    + cliente.getNroCliente() + "]";
	}
    }
}