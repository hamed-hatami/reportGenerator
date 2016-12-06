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


@FacesValidator(value = "dateValidator")
public class DateValidator implements Validator {

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        UserManagementAction me = ManagedBeanManager.lookup(UserManagementAction.class);
        if (object instanceof String) {
            String inputText = (String) object;
            if (!inputText.trim().isEmpty()) {
                Pattern pattern = Pattern.compile(Configuration.getProperty("date_regular"));
                Matcher matcher = pattern.matcher(inputText);
                if (!matcher.matches()) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary(me.getBundleMessage("error.date.format"));
                    throw new ValidatorException(message);
                }
            }
        }
    }
}