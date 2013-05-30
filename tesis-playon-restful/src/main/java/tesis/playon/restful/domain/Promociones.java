package tesis.playon.restful.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Promociones implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @XmlElement(required = true)
    List<Promocion> promociones;

    @XmlElement(required = false)
    public List<Promocion> getLista() {
        return promociones;
    }

    public void setLista(List<Promocion> promociones) {
        this.promociones = promociones;
    }
}