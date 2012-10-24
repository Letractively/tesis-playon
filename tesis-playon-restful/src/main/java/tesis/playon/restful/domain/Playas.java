package tesis.playon.restful.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Playas implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @XmlElement(required = true)
    List<Playa> playas;

    @XmlElement(required = false)
    public List<Playa> getLista() {
        return playas;
    }

    public void setLista(List<Playa> playas) {
        this.playas = playas;
    }
}