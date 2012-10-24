package tesis.playon.mobile.json.model;

import java.io.Serializable;
import java.util.List;

public class Playas implements Serializable {

    private static final long serialVersionUID = -4573436968149297463L;

    private List<Playa> lista;

    public List<Playa> getPlayas() {
        return lista;
    }

    public void setPlayas(List<Playa> lista) {
        this.lista = lista;
    }
}