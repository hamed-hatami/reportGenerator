package ir.dotin.core.action;


import ir.dotin.core.utils.CookieUtil;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


@Named(value = "logoutAction")
@SessionScoped
public class LogoutAction implements Serializable {

    @Inject
    private UserManagementAction me;

    public String logout() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (CookieUtil.removeCookie(facesContext, "locale"))
            CookieUtil.setCookie(facesContext, "locale", FacesContext.getCurrentInstance().getViewRoot().getLocale().toString());

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute(UserManagementAction.INVALID_TRY_MAP);
        session.removeAttribute(UserManagementAction.usernameInSession);
        session.invalidate();
        //generalHelper.getSessionLogService().createSessionLog(new SessionLog(LangUtils.getEnglishNumber(CalendarUtil.getTime(new Date(), new Locale("fa"))), me.getParticipant().getBic(), me.getUsername(), "logout"));
        return "login";
    }


}