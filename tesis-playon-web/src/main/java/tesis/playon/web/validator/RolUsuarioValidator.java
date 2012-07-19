package tesis.playon.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import tesis.playon.web.model.RolUsuario;

@Component
public class RolUsuarioValidator implements Validator {

    public boolean supports(Class<?> c) {
	return RolUsuario.class.isAssignableFrom(c);
    }

    public void validate(Object command, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "field.name.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "field.standard.empty");
    }
}
