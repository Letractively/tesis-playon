package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1378629978330011740L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("password")
    private String password;

    @SerializedName("nombreUser")
    private String nombreUser;

    @SerializedName("apellido")
    private String apellido;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("email")
    private String email;

    @SerializedName("nroDoc")
    private int nroDoc;

    @SerializedName("enable")
    private Boolean enable;

    private TipoDoc tipoDoc;

    private Playa playa;

    private FotoUsuario foto;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getNombreUser() {
	return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
	this.nombreUser = nombreUser;
    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public int getNroDoc() {
	return nroDoc;
    }

    public void setNroDoc(int nroDoc) {
	this.nroDoc = nroDoc;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Boolean getEnable() {
	return enable;
    }

    public void setEnable(Boolean enable) {
	this.enable = enable;
    }

    public TipoDoc getTipoDoc() {
	return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
	this.tipoDoc = tipoDoc;
    }

    public FotoUsuario getFoto() {
	return foto;
    }

    public void setFoto(FotoUsuario foto) {
	this.foto = foto;
    }

}