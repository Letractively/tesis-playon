package tesis.playon.mobile.json.model;

import java.io.Serializable;
import java.util.List;

public class Playas implements Serializable {

    private static final long serialVersionUID = -4573436968149297463L;

    private List<Playa> playas;

    public List<Playa> getPlayas() {
        return playas;
    }

    public void setPlayas(List<Playa> playas) {
        this.playas = playas;
    }
}