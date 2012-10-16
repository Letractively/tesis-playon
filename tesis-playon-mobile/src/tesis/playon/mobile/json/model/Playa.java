package tesis.playon.mobile.json.model;

import com.google.gson.annotations.SerializedName;

public class Playa {

    @SerializedName("id")
    private Integer id;

    @SerializedName("domicilio")
    private String domicilio;

    @SerializedName("telefono")
    private String telefono;

    @SuppressWarnings("unused")
    private Barrio barrio;

    @SerializedName("email")
    private String email;

    @SerializedName("cuit")
    private String cuit;

    @SerializedName("longitud")
    private Double longitud;

    @SerializedName("latitud")
    private Double latitud;

    @SuppressWarnings("unused")
    private EstadoPlaya estado;

    @SerializedName("disponibilidad")
    private Integer disponibilidad;

    @SerializedName("nombreComercial")
    private String nombreComercial;

    @SerializedName("razonSocial")
    private String razonSocial;

}