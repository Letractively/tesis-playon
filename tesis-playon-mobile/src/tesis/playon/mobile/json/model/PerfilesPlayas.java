package tesis.playon.mobile.json.model;

import java.io.Serializable;
import java.util.List;

public class PerfilesPlayas implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    private List<PerfilPlaya> lista;

    public List<PerfilPlaya> getPerfilesPlayas() {
	return lista;
    }

    public void setPerfilesPlayas(List<PerfilPlaya> lista) {
	this.lista = lista;
    }
}