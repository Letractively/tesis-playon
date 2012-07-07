/**
 * 
 */
package tesis.playon.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Pablo
 *
 */
@Entity
@Table(name = "favorito", catalog = "tesis_playon")
public class Favorito implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    private Playa playa;
    
    private Cliente cliente;
}
