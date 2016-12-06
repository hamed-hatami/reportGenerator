package ir.dotin.core.action;


import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.entity.generic.User;
import ir.dotin.core.utils.Configuration;
import ir.dotin.core.utils.CookieUtil;
import ir.dotin.core.utils.LangUtils;
import org.apache.commons.io.IOUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;

@Named(value = "me")
@SessionScoped
public class UserManagementAction implements Serializable {

    public static final String INVALID_TRY_MAP = "invalid_try_map";
    public static final String usernameInSession = "username";
    private static final String BUNDLE_NAME = "ir.dotin.core.resources.Messages";
    private String captchaValue;

    @Inject
    private AccessControlAction accessControlAction;
    @Inject
    private LogoutAction logoutAction;
    @Inject
    private GeneralHelper generalHelper;

    private Locale locale = null;
    private String changeCurrentUserPassword, message, rePassword = "", signature = "", username, password = "", OldPassword = "";
    private User user = null;
    private int activeUserCount = 0;
    private int activeSessionCount = 0;
    private int sessionLogPage = 1;
    private List<String> chatMessages = new ArrayList<>();

    public String beginSessionLog() {
        return "user-status";
    }

    private boolean seeMenuOfSystem;

    public String getRemoteIpAddress() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getRemoteAddr();
    }


    public void redirect(String pageName) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (facesContext.getExternalContext().getRequestParameterMap().get("cid") == null || facesContext.getExternalContext().getRequestParameterMap().get("cid").isEmpty()) {
                facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + pageName);
            } else {
                facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + pageName + "?cid=" + facesContext.getExternalContext().getRequestParameterMap().get("cid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String authenticate() {

        try {

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            if ((username == null) || (username.isEmpty()) || (password == null) || (password.isEmpty())) {
                addErrorMessage("empty.username.or.password");
                return "login";
            }

            user = generalHelper.getUserService().authenticate(username, password);
            if (user == null) {
                addErrorMessage("user.not.authenticated");
                return "login";
            }

            accessControlAction.setWorkgroups(user.getWorkgroups());


            if ("true".equals(user.getIsFirstLogin())) {
                session.setAttribute(this.usernameInSession, this.username);
                changeCurrentUserPassword = "false";
                return "change-password";
            }

            if (user.getWorkgroups().parallelStream().anyMatch(workgroup -> workgroup.getRoles().size() == 0)) {
                addErrorMessage("user.has.noRole");
                return "login";
            }

            session.setAttribute(this.usernameInSession, this.username);

        } catch (Exception e) {
            e.printStackTrace();
            addErrorMessage("operation.not.occurred");
            return "login";
        }

        return "home";
    }

    public String enterToSystem() {
        seeMenuOfSystem = true;
        System.out.println();
        return "home";
    }

    public String gotoHome() {
        return "home";
    }

    public String download() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.responseReset();
            externalContext.setResponseContentType("application/x-zip-compressed");
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=setup.zip");
            externalContext.setResponseHeader("Cache-Control", "no-cache");
            byte[] content = IOUtils.toByteArray(UserManagementAction.class.getClassLoader().getResourceAsStream("files" + File.separator + "setup.zip"));
            externalContext.setResponseContentLength(content.length);
            externalContext.setResponseStatus(200);
            externalContext.getResponseOutputStream().write(content);
            externalContext.getResponseOutputStream().flush();
            facesContext.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public void readCookie() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long startTime = 0;
        try {
            startTime = Long.parseLong(CookieUtil.fetchCookie(request, "startTime"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String user_login_penalty_second = Configuration.getProperty("user_login_penalty_second");
        long currentTime = new Date().getTime();
    }

    private void disableLogin() {
        addErrorMessage("invalid.username.or.password"); //remove this error msg, if readCooki is enable!

        Cookie timeCookie = new Cookie("startTime", String.valueOf(new Date().getTime()));
        timeCookie.setMaxAge(-1);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addCookie(timeCookie);
    }


    public String resetPassword() {
        try {
            if (validateRequired()) {
//                user.setPassword(generalHelper.getEncryptUtil().encrypt(password));
                user.setIsFirstLogin("false");
                //generalHelper.getUserService().editUser(user);
                addInfoMessage("info.password.modify");
                return logoutAction.logout();
            }
        } catch (Exception e) {
            e.printStackTrace();
            addErrorMessage("operation.not.occurred");
            return "password-reset";
        }
        return "password-reset";
    }

    private boolean validateRequired() {
        StringBuffer errorMessage = new StringBuffer();
//        try {
//            if (changeCurrentUserPassword.equalsIgnoreCase("true")) {
//                if (!(generalHelper.getEncryptUtil().encrypt(OldPassword).equals(user.getPassword()))) {
//                    errorMessage.append(getBundleMessage("error.password.old_not_match"));
//                    errorMessage.append(getBundleMessage(" "));
//                    addErrorMessage(errorMessage.toString());
//                    OldPassword = "";
//                    password = "";
//                    rePassword = "";
//                    return false;
//                }
//            }
//            if (generalHelper.getEncryptUtil().encrypt(password).equals(user.getPassword())) {
//                errorMessage.append(getBundleMessage("error.old.pass.equal.new"));
//                errorMessage.append(getBundleMessage(" "));
//                addErrorMessage(errorMessage.toString());
//                return false;
//            }
//            if (password.isEmpty() || rePassword.isEmpty()) {
//                errorMessage.append(getBundleMessage("choose"));
//                errorMessage.append(" ");
//                errorMessage.append(getBundleMessage("passwordOrRePassword"));
//                errorMessage.append(" ");
//                errorMessage.append(getBundleMessage("isRequired"));
//                addErrorMessage(errorMessage.toString());
//                return false;
//            }
//            if (!password.equals(rePassword)) {
//                errorMessage.delete(0, errorMessage.length());
//                errorMessage.append(getBundleMessage("error.password.not_match"));
//                addErrorMessage(errorMessage.toString());
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;

    }

    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle(BUNDLE_NAME, getLocale());
    }

    public String getBundleMessage(String bundleKey, String... arguments) {
        if (bundleKey == null || bundleKey.isEmpty())
            return "?";

        try {
            return MessageFormat.format(getBundle().getString(bundleKey), arguments);
        } catch (MissingResourceException e) {
//todo: don't log here please!!
//            log.error(createExceptionCause(e));
            return bundleKey;
        }
    }

    public String changePassword() {
        changeCurrentUserPassword = "true";
        return "password-reset";

    }

    public String revert() {
        changeCurrentUserPassword = "false";
        return "home";

    }

    public void changeLocale() {
        FacesContext context = FacesContext.getCurrentInstance();
        String language = context.getViewRoot().getLocale().getLanguage();
        String currentPage = context.getViewRoot().getViewId().replaceAll(".xhtml", ".htm");

        if (language == null) {
            this.setLocale(Locale.ENGLISH);
        }

        if (language.equalsIgnoreCase("fa")) {
            this.setLocale(Locale.ENGLISH);
        } else {
            this.setLocale(LangUtils.LOCALE_FARSI);
        }

        redirect(currentPage);
    }

    public void addErrorMessage(String bundleKey) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundleMessage(bundleKey, null), getBundleMessage(bundleKey, null)));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void addErrorMessageString(String messageContent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messageContent, messageContent));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void addErrorMessage(String bundleKey, String... arguments) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundleMessage(bundleKey, arguments), getBundleMessage(bundleKey, arguments)));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void addWarnMessage(String bundleKey) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getBundleMessage(bundleKey, null), getBundleMessage(bundleKey, null)));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void addInfoMessage(String bundleKey) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundleMessage(bundleKey, null), getBundleMessage(bundleKey, null)));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void addInfoMessage(String bundleKey, String... arguments) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundleMessage(bundleKey, arguments), getBundleMessage(bundleKey, arguments)));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public Locale getLocale() {
        if (this.locale == null) {
            this.locale = LangUtils.LOCALE_FARSI;
        }
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getChangeCurrentUserPassword() {
        return changeCurrentUserPassword;
    }

    public void setChangeCurrentUserPassword(String changeCurrentUserPassword) {
        this.changeCurrentUserPassword = changeCurrentUserPassword;
    }

//    public Participant getParticipant() {
//        return participant;
//    }
//
//    public void setParticipant(Participant participant) {
//        this.participant = participant;
//    }
//
//    public String getCaptchaValue() {
//        return captchaValue;
//    }
//
//    public void setCaptchaValue(String captchaValue) {
//        this.captchaValue = captchaValue;
//    }
//
//    public String getSessionLogStatus() {
//        return sessionLogStatus;
//    }
//
//    public void setSessionLogStatus(String sessionLogStatus) {
//        this.sessionLogStatus = sessionLogStatus;
//    }
//
//
//    public List<WorkGroup> getWorkGroupsItem() {
//        return workGroupsItem;
//    }
//
//    public void setWorkGroupsItem(List<WorkGroup> workGroupsItem) {
//        this.workGroupsItem = workGroupsItem;
//    }

//    public List<Participant> getParticipantItem() {
//        return participantItem;
//    }
//
//    public void setParticipantItem(List<Participant> participantItem) {
//        this.participantItem = participantItem;
//    }

//    public Storage<Long, WorkGroup> getWorkGroupStorage() {
//        return workGroupStorage;
//    }
//
//    public void setWorkGroupStorage(Storage<Long, WorkGroup> workGroupStorage) {
//        this.workGroupStorage = workGroupStorage;
//    }
//
//    public Storage<Long, Participant> getParticipantStorage() {
//        return participantStorage;
//    }
//
//    public void setParticipantStorage(Storage<Long, Participant> participantStorage) {
//        this.participantStorage = participantStorage;
//    }

    public int getActiveUserCount() {
        return activeUserCount;
    }

    public void setActiveUserCount(int activeUserCount) {
        this.activeUserCount = activeUserCount;
    }

    public int getActiveSessionCount() {
        return activeSessionCount;
    }

    public void setActiveSessionCount(int activeSessionCount) {
        this.activeSessionCount = activeSessionCount;
    }

    public int getSessionLogPage() {
        return sessionLogPage;
    }

    public void setSessionLogPage(int sessionLogPage) {
        this.sessionLogPage = sessionLogPage;
    }

//    public WorkGroup getWorkGroup() {
//        return workGroup;
//    }
//
//    public void setWorkGroup(WorkGroup workGroup) {
//        this.workGroup = workGroup;
//    }
//
//    public boolean isLoginEnable() {
//        return loginEnable;
//    }
//
//    public void setLoginEnable(boolean loginEnable) {
//        this.loginEnable = loginEnable;
//    }
//
//    public Storage<Long, State> getStateStorage() {
//        return stateStorage;
//    }
//
//    public void setStateStorage(Storage<Long, State> stateStorage) {
//        this.stateStorage = stateStorage;
//    }
//
//    public Initializer getInitializer() {
//        return initializer;
//    }
//
//    public List<State> getStateItem() {
//
//        if (stateItem == null || stateItem.isEmpty()) {
//            stateItem = generalHelper.getStateService().getAllState();
//            for (State state : stateItem) {
//                stateStorage.put(state.getId(), state);
//            }
//        }
//        return stateItem;
//    }

//    public void setStateItem(List<State> stateItem) {
//        this.stateItem = stateItem;
//    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public int getChatMessagesSize() {
        if (chatMessages == null) return 0;
        return chatMessages.size();
    }

    public List<String> getChatMessages() {
        return chatMessages;
    }

    public GeneralHelper getGeneralHelper() {
        return generalHelper;
    }

    public void setGeneralHelper(GeneralHelper generalHelper) {
        this.generalHelper = generalHelper;
    }

    public Boolean getSeeMenuOfSystem() {
        return seeMenuOfSystem;
    }

    public void setSeeMenuOfSystem(Boolean seeMenuOfSystem) {
        this.seeMenuOfSystem = seeMenuOfSystem;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }
}