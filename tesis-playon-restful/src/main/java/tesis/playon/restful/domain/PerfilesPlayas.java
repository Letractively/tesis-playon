package tesis.playon.restful.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PerfilesPlayas implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @XmlElement(required = true)
    List<PerfilPlaya> perfilesPlayas;

    @XmlElement(required = false)
    public List<PerfilPlaya> getLista() {
	return perfilesPlayas;
    }

    public void setLista(List<PerfilPlaya> perfilesPlayas) {
	this.perfilesPlayas = perfilesPlayas;
    }
}