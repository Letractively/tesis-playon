package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class FotoUsuario implements Serializable {

    private static final long serialVersionUID = 2025012255601511594L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("fotoUsuario")
    private String fotoUsuario;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getFotoUsuario() {
	return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
	this.fotoUsuario = fotoUsuario;
    }

}
