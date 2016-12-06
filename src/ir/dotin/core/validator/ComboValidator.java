package ir.dotin.core.validator;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value = "comboValidator")
public class ComboValidator implements Validator {

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
        if ((object instanceof String)) {
            String value = ((String) object).trim();
            if (value.equalsIgnoreCase("0")) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(me.getBundleMessage("javax.faces.component.UIInput.REQUIRED"));
                throw new ValidatorException(message);
            }
        }
        if ((object instanceof Long)) {
            long value = ((Long) object).longValue();
            if (value == 0L) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(me.getBundleMessage("javax.faces.component.UIInput.REQUIRED"));
                throw new ValidatorException(message);
            }
        }
    }
}