package ir.dotin.core.validator;


import ir.dotin.core.utils.ManagedBeanManager;
import ir.dotin.core.action.UserManagementAction;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@FacesValidator(value = "timeValidator")
public class TimeValidator implements Validator {

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
        if (object instanceof String) {
            String inputText = (String) object;
            if (!inputText.trim().isEmpty()) {
                Pattern pattern = Pattern.compile("([0][1-9]|[1][0-9]|[2][0-3]):[0-5][0-9]");
                Matcher matcher = pattern.matcher(inputText);
                if (!matcher.matches()) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary(me.getBundleMessage("error.time.invalid"));
                    throw new ValidatorException(message);
                }
            }
        }
    }
}