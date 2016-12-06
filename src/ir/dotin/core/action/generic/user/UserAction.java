package ir.dotin.core.action.generic.user;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.*;
import ir.dotin.core.model.entity.generic.User;
import ir.dotin.core.model.entity.generic.Workgroup;
import ir.dotin.core.utils.Configuration;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Named("userAction")
@SessionScoped
public class UserAction implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    private List<User> userList = null;
    private User currentUser = null;
    private User newUser = null;

    private List<Workgroup> workgroupList;
    private List<Workgroup> selectedWorkgroups = new ArrayList<>();
    private String selectedOrganizationType;
    private Object selectedOrganize;

    public String begin() {
        refresh();
        return "list-user";
    }

    private void refresh() {
        userList = new ArrayList<>(generalHelper.getUserService().getAllUser());
        workgroupList = new ArrayList<>(generalHelper.getWorkgroupService().getAllWorkGroup());
    }

    public void doDelete() {
        if (generalHelper.getUserService().deleteUser(currentUser)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newUser = new User();
        selectedWorkgroups = new ArrayList<>();

    }

    public void doAdd() {

        if (generalHelper.getUserService().exist(newUser.getUsername())) {
            me.addErrorMessage("user_is_exit");
        } else {
            newUser.setDeleted("0");
            newUser.setEventDate(new Date().toString());
            newUser.setEventBy(me.getUser().getUsername());
            newUser.setEventType(EventType.CREATE);
            newUser.setWorkgroups(new HashSet<>(selectedWorkgroups));
            newUser = generalHelper.getUserService().createUser(newUser);
            if (newUser != null) {
                refresh();
                me.addInfoMessage("operation.occurred");
            } else {
                me.addErrorMessage("operation.not.occurred");
            }

        }


    }

    public void edit() {
        selectedWorkgroups = new ArrayList<>(currentUser.getWorkgroups());
    }

    public void doEdit() {
        currentUser.setWorkgroups(new HashSet<>(selectedWorkgroups));
        User userEdited = generalHelper.getUserService().editUser(currentUser);
        if (userEdited != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public List<Workgroup> getWorkgroupList() {
        return workgroupList;
    }

    public void setWorkgroupList(List<Workgroup> workgroupList) {
        this.workgroupList = workgroupList;
    }

    public List<Workgroup> getSelectedWorkgroups() {
        return selectedWorkgroups;
    }

    public void setSelectedWorkgroups(List<Workgroup> selectedWorkgroups) {
        this.selectedWorkgroups = selectedWorkgroups;
    }

    public String getSelectedOrganizationType() {
        return selectedOrganizationType;
    }

    public void setSelectedOrganizationType(String selectedOrganizationType) {
        this.selectedOrganizationType = selectedOrganizationType;
    }

    public Object getSelectedOrganize() {
        return selectedOrganize;
    }

    public void setSelectedOrganize(Object selectedOrganize) {
        this.selectedOrganize = selectedOrganize;
    }
}
