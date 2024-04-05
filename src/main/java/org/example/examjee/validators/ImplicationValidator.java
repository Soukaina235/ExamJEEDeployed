package org.example.examjee.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator(value = "implicationValidator")
public class ImplicationValidator implements Validator{

    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        double input = (double) value;

        if (input <= 0 || input >= 100) { // the entry is valid if its value is > 0 or <= 100
            ResourceBundle bundle = ResourceBundle.getBundle("i18n.labels");
            FacesMessage message = new FacesMessage(bundle.getString("invalidInput"), bundle.getString("invalidInputDetails"));
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}