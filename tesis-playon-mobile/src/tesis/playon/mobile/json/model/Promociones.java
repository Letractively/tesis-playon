package tesis.playon.mobile.json.model;

import java.io.Serializable;
import java.util.List;

public class Promociones implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    private List<Promocion> lista;

    public List<Promocion> getPromociones() {
	return lista;
    }

    public void setPromociones(List<Promocion> lista) {
	this.lista = lista;
    }
}