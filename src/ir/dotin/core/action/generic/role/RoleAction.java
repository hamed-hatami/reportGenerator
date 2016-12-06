package ir.dotin.core.action.generic.role;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.generic.Role;
import org.apache.commons.lang3.StringUtils;
import org.richfaces.component.SortOrder;


import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.*;

@Named("roleAction")
@SessionScoped
public class RoleAction implements Serializable {


    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    private List<Role> roleList = null;
    private Role currentRole = null;
    private Role newRole = null;

    public String begin() {
        refresh();
        return "list-role";
    }

    private void refresh() {
        roleList = generalHelper.getRoleService().getAllRole();
    }

    public void doDelete() {
        if (generalHelper.getRoleService().deleteRole(currentRole)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newRole = new Role();

    }

    public String doAdd() {

      /*  if (generalHelper.getRoleService().findByName(newRole.getName())!= null) {
            me.addErrorMessage("role_exist");
            return null;
        } else {
            newRole.setDeleted("0");
            newRole.setEventDate(new Date().toString());
            newRole.setEventType(EventType.CREATE);
            newRole = generalHelper.getRoleService().createRole(newRole);
            if (newRole != null) {
                refresh();
                me.addInfoMessage("operation.occurred");
            } else {
                me.addErrorMessage("operation.not.occurred");
            }
        }*/
        return "list-role";

    }

    public void edit() {

    }

    public String doEdit() {
        Role roleEdited = generalHelper.getRoleService().editRole(currentRole);
        if (roleEdited != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
        return "list-role";
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    public Role getNewRole() {
        return newRole;
    }

    public void setNewRole(Role newRole) {
        this.newRole = newRole;
    }


}