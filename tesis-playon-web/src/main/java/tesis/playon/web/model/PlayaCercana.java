/**
 * 
 */
package tesis.playon.web.model;


/**
 * @author pablo
 *
 */
public class PlayaCercana {
    
    private int playaID;
    
    private String domicilio;
    
    private String nombreComercial;
    
    private double distancia;

    public PlayaCercana(int playaID, String domicilio, String nombreComercial, double distancia) {
	super();
	this.playaID = playaID;
	this.domicilio = domicilio;
	this.nombreComercial = nombreComercial;
	this.distancia = distancia;
    }

    public PlayaCercana() {
	super();
    }

    public int getPlayaID() {
        return playaID;
    }

    public void setPlayaID(int playaID) {
        this.playaID = playaID;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
