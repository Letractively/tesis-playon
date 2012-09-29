package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Mail;
import tesis.playon.web.util.NotificadorUtil;

@ManagedBean(name = "contactoMB")
@RequestScoped
public class TestMB implements Serializable {

    public String test()

    {
	return "";
    }

    

}
