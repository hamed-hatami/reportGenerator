package ir.dotin.core.validator;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.ManagedBeanManager;
import nl.captcha.Captcha;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

@FacesValidator(value = "captchaValidator")
public class CaptchaValidator implements Validator {
    private UserManagementAction me = ManagedBeanManager.lookup(UserManagementAction.class);

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        boolean response = false;
        String answer = (String) object;
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Captcha captcha = (Captcha) session.getAttribute("simpleCaptcha");

        if (captcha != null) {
            response = captcha.getAnswer().equalsIgnoreCase(answer);
        }

        if (!response) {
            throw new ValidatorException(new FacesMessage(me.getBundleMessage("invalidCaptcha")));
        }
    }
}