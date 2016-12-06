package ir.dotin.core.validator;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.Configuration;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);

    public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
        String email = (String) object;
        Pattern pattern = Pattern.compile(Configuration.getProperty("email_regular"));
        Matcher matcher = pattern.matcher(email);
        if (email != null && !matcher.matches()) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(me.getBundleMessage("invalid.email"));
            throw new ValidatorException(message);
        }
    }
}