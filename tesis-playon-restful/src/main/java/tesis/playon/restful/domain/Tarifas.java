package tesis.playon.restful.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tarifas implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @XmlElement(required = true)
    List<Tarifa> tarifas;

    @XmlElement(required = false)
    public List<Tarifa> getLista() {
        return tarifas;
    }

    public void setLista(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
}