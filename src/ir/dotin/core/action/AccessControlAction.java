package ir.dotin.core.action;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.model.WorkGroupType;
import ir.dotin.core.model.entity.generic.Role;
import ir.dotin.core.model.entity.generic.Workgroup;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

@Named(value = "accessControlAction")
@SessionScoped
public class AccessControlAction implements Serializable {



    @Inject
    private UserManagementAction me;

    private Set<Workgroup> workgroups;

    public boolean isHeadquarter() {
        return workgroups.parallelStream().anyMatch(workgroup -> workgroup.getName().equals(WorkGroupType.HEADQUARTER.getDescription()));
    }

    public boolean isBranch() {
        return workgroups.parallelStream().anyMatch(workgroup -> workgroup.getName().equals(WorkGroupType.BRANCH.getDescription()));
    }

    public boolean isSystemManager() {
        return workgroups.parallelStream().anyMatch(workgroup -> workgroup.getName().equals(WorkGroupType.SYSTEMMANAGER.getDescription()));
    }

    public boolean isSuperUser() {
        return workgroups.parallelStream().anyMatch(workgroup -> workgroup.getName().equals(WorkGroupType.SUPERUSER.getDescription()));
    }

    public boolean hasRole(String roleName) {

        if ("login".equalsIgnoreCase(roleName) || "home".equalsIgnoreCase(roleName))
            return true;

        if (me.getUser().getUsername().equalsIgnoreCase("admin")) {
            return true;
        }

        for(Workgroup workgroup : workgroups){
            for(Role role : workgroup.getRoles())
                if(role.getName().equalsIgnoreCase(roleName)){
                  return true;
                }
        }

        //return workgroups.parallelStream().anyMatch(workgroup -> workgroup.getRoles().contains(roleName));
        return false;
    }

    public void hasAccess(String viewId) {
        try {
            String roleName = viewId.substring(viewId.lastIndexOf("/") + 1, viewId.indexOf("."));
            if (!hasRole(roleName)) {
                HttpServletResponse response = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse());
                HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/access-denied.htm"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canSeemenu(String s) {

        if (me.getUser().getUsername().equalsIgnoreCase("admin")) {
            return true;
        }
        for (Workgroup workgroup : workgroups) {
            for (Role role : workgroup.getRoles()) {
                if (role.getName().equalsIgnoreCase(s))
                    return true;
            }
        }

        return false;
    }

    public Set<Workgroup> getWorkgroups() {
        return workgroups;
    }

    public void setWorkgroups(Set<Workgroup> workgroups) {
        this.workgroups = workgroups;
    }

}
