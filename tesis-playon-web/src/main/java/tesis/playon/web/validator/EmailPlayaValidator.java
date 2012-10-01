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

import tesis.playon.web.dao.IPlayaDao;
import tesis.playon.web.dao.impl.PlayaDao;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.impl.PlayaService;

/**
 * @author pablo
 * 
 */
@FacesValidator("emailPlayaValidator")
public class EmailPlayaValidator implements Validator{

    IPlayaService playaService;
    
    IPlayaDao playaDao;
    
    public void validate(FacesContext cntx, UIComponent cmp, Object val) {
	
	String email = (String) val;
	
	playaService = new PlayaService();
	
	playaDao = new PlayaDao();
	
	if(playaDao.existeEmail(email)){
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR
		    , "El email " + email + "ya se encuentra registrado.", null);
	    throw new ValidatorException(message);
	}
    }

    public IPlayaService getPlayaService() {
        return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
        this.playaService = playaService;
    }

    public IPlayaDao getPlayaDao() {
        return playaDao;
    }

    public void setPlayaDao(IPlayaDao playaDao) {
        this.playaDao = playaDao;
    }
    
    
}