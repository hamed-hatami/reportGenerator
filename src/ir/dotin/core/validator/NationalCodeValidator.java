package ir.dotin.core.validator;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value = "nationalCodeValidator")
public class NationalCodeValidator implements Validator {

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
        if ((object instanceof String)) {
            String value = ((String) object).trim();
            if (!isValidNationalCode(value)) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(me.getBundleMessage("national.code.exception"));
                throw new ValidatorException(message);
            }
        }
    }

    private boolean isValidNationalCode(String nationalCode) {
        boolean flag = true;
        if (nationalCode.isEmpty())
            flag = true;
        else if (nationalCode.length() < 10)
            flag = false;
        return flag;
    }
}