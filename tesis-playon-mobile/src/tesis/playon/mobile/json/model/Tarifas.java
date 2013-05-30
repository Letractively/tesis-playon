package tesis.playon.mobile.json.model;

import java.io.Serializable;
import java.util.List;

public class Tarifas implements Serializable {

    private static final long serialVersionUID = -4573436968149297463L;

    private List<Tarifa> lista;

    public List<Tarifa> getTarifas() {
        return lista;
    }

    public void setTarifas(List<Tarifa> lista) {
        this.lista = lista;
    }
}