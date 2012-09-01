package tesis.playon.web.model;

public class Mail {

    private String nombre;
    private String apellido;
    private String asuntoContacto;
    private String mensaje;
    private String destinatario;
    private String asuntoNuevoUsuario;
    private String datosContacto;

    public Mail() {

    }

    public Mail(String asunto, String mensaje, String destinatario)

    {

	this.asuntoContacto = asunto;
	this.mensaje = mensaje;
	this.destinatario = destinatario;

    }

    public String getAsuntoContacto() {
	return datosContacto + asuntoContacto;
    }

    public void setAsuntoContacto(String asunto) {
	this.asuntoContacto = asunto;
    }

    public String getMensaje() {
	return mensaje;
    }

    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
    }

    public String getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(String destinatario) {
	this.destinatario = destinatario;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public void setDatosContacto()

    {
	datosContacto = "La persona " + nombre + " " + apellido + " " + destinatario
		+ " realizo la siguiente consulta: ";

    }

    public String getAsuntoNuevoUsuario() {
	return "Felicitaciones, ahora es cliente de PLAYON - RED DE PLAYAS DE ESTACIONAMIENTO";
    }

    public void setAsuntoNuevoUsuario(String asuntoNuevoUsuario) {
	this.asuntoNuevoUsuario = asuntoNuevoUsuario;
    }

}
