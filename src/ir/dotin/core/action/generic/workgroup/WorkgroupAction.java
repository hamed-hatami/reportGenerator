package ir.dotin.core.action.generic.workgroup;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.generic.Role;
import ir.dotin.core.model.entity.generic.Workgroup;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Named("workgroupAction")
@SessionScoped
public class WorkgroupAction implements Serializable {


    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;


    private List<Workgroup> workgroupList = null;
    private Workgroup currentWorkgroup = null;
    private Workgroup newWorkgroup = null;
    private List<Role> roleList = new ArrayList<>();
    private List<Role> selectedRoles = new ArrayList<>();

    public String begin() {
        refresh();
        return "list-workgroup";
    }

    private void refresh() {
        workgroupList = new ArrayList<>(generalHelper.getWorkgroupService().getAllWorkGroup());
        initWorkGroup();
    }

    public void initWorkGroup() {
        if (me.getUser().getUsername().equalsIgnoreCase("admin")) {
            roleList = new ArrayList<>(generalHelper.getRoleService().getAllRole());
        } else {
            for (Workgroup workgroup : me.getUser().getWorkgroups()) {
                roleList.addAll(workgroup.getRoles());
            }
        }
    }

    public void doDelete() {
        if (generalHelper.getWorkgroupService().deleteWorkGroup(currentWorkgroup)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newWorkgroup = new Workgroup();
        selectedRoles = new ArrayList<>();
    }

    public void doAdd() {
        newWorkgroup.setEventBy(me.getUser().getUsername());
        newWorkgroup.setEventType(EventType.CREATE);
        newWorkgroup.setDeleted("0");
        newWorkgroup.setRoles(new HashSet<>(selectedRoles));
        newWorkgroup = generalHelper.getWorkgroupService().createWorkGroup(newWorkgroup);
        if (newWorkgroup != null) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }

    }

    public void edit() {
        selectedRoles = new ArrayList<>(currentWorkgroup.getRoles());
    }

    public void doEdit() {
        currentWorkgroup.setRoles(new HashSet<>(selectedRoles));
        currentWorkgroup = generalHelper.getWorkgroupService().editWorkGroup(currentWorkgroup);
        if (currentWorkgroup != null) {

            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }


    public List<Workgroup> getWorkgroupList() {
        return workgroupList;
    }

    public void setWorkgroupList(List<Workgroup> workgroupList) {
        this.workgroupList = workgroupList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Workgroup getCurrentWorkgroup() {
        return currentWorkgroup;
    }

    public void setCurrentWorkgroup(Workgroup currentWorkgroup) {
        this.currentWorkgroup = currentWorkgroup;
    }

    public Workgroup getNewWorkgroup() {
        return newWorkgroup;
    }

    public void setNewWorkgroup(Workgroup newWorkgroup) {
        this.newWorkgroup = newWorkgroup;
    }

    public List<Role> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<Role> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

}
