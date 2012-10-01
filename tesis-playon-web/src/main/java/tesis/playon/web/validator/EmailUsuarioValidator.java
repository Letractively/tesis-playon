/**
 * 
 */
package tesis.playon.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import tesis.playon.web.managed.bean.UsuarioManagedBean;

/**
 * @author pablo
 * 
 */
@FacesValidator("emailUsuarioValidator")
public class EmailUsuarioValidator implements Validator {
    
    public void validate(FacesContext cntx, UIComponent cmp, Object val) {

	String email = (String) val;
	
	UsuarioManagedBean usuarioMB= new UsuarioManagedBean();
	
	if (usuarioMB.getUsuarioService().existeEmail(email)) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email " + email
		    + "ya se encuentra registrado.", null);
	    throw new ValidatorException(message);
	}
    }
}