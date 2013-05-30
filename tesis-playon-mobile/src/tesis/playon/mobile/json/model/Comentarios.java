package tesis.playon.mobile.json.model;

import java.io.Serializable;
import java.util.List;

public class Comentarios implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    private List<Comentario> lista;

    public List<Comentario> getComentarios() {
        return lista;
    }

    public void setComentarios(List<Comentario> lista) {
        this.lista = lista;
    }
}