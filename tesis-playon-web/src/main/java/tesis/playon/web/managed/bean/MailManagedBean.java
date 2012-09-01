package tesis.playon.web.managed.bean;

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tesis.playon.web.model.Mail;

@ManagedBean(name = "mailMB")
@RequestScoped
public class MailManagedBean {

    private Mail mail;

    private static final long serialVersionUID = -1085389423375986168L;

    static final String username = "tesisplayon@gmail.com";
    static final String password = "tesisplayon2012";
    private static Properties props = new Properties();

    public MailManagedBean() {

    }

    public void enviar(Mail mail) {
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	    }
	});

	try {

	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(username));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getDestinatario()));
	    message.setSubject(mail.getAsuntoContacto());
	    message.setText(mail.getMensaje());

	    Transport.send(message);

	} catch (MessagingException e) {
	    throw new RuntimeException(e);
	}
    }

    public void setMail(Mail mail) {

	this.mail = mail;
    }

    public Mail getMail()

    {
	return mail;

    }

}
