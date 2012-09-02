package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import tesis.playon.web.model.Mail;

@ManagedBean(name = "contactoMB")
@RequestScoped
public class contactoMB implements Serializable{

    private static final long serialVersionUID = -1085389423375986168L;
    
    private Mail mail;
    private MailManagedBean mailMB;

    public void enviarMailContacto()

    {
	mail.setDatosContacto();
	mail.setDestinatario("tesisplayon@gmail.com");
	mailMB.enviar(mail);

    }

    public Mail getMail() {
	return mail;
    }

    public void setMail(Mail mail) {
	this.mail = mail;
    }

}
