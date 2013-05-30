package tesis.playon.restful.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comentarios implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @XmlElement(required = true)
    List<Comentario> comentarios;

    @XmlElement(required = false)
    public List<Comentario> getLista() {
	return comentarios;
    }

    public void setLista(List<Comentario> comentarios) {
	this.comentarios = comentarios;
    }
}