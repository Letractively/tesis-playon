package tesis.playon.mobile.json.model;

import com.google.gson.annotations.SerializedName;

public class RolesPorUsuario {

    @SerializedName("id")
    private Integer id;

    @SerializedName("usuario")
    private String usuario;

    @SerializedName("rol")
    private String rol;

}