package ir.dotin.core.validator;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value = "URLValidator")
public class URLValidator implements Validator {

    private static final String PATTERN = "^[a-zA-Z0-9\\-\\.]+\\.(com|org|net|mil|edu|COM|ORG|NET|MIL|EDU|ir|IR)$";

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
        if ((object instanceof String)) {
            String value = ((String) object).trim();
            if (!value.matches(PATTERN) && !value.isEmpty()) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(me.getBundleMessage("url.validate.exception"));
                throw new ValidatorException(message);
            }
        }
    }
}