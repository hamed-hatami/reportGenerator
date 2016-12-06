package ir.dotin.core.action.baseInformation;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.BaseInformationChild;
import ir.dotin.core.model.entity.BaseInformationParent;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr_safiary on 10/13/2016.
 */
@Named("baseInformationChildAction")
@SessionScoped
public class BaseInformationChildAction implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    private List<BaseInformationChild> baseInformationChildList = null;
    private BaseInformationChild currentBaseInformationChild = null;
    private BaseInformationChild newBaseInformationChild = null;
    private BaseInformationParent selectedBaseInformationParent;

    public String begin() {
        refresh();
        return "list-baseInformationChild";
    }

    private void refresh() {
        baseInformationChildList = generalHelper.getBaseInformationChildService().getAllBaseInformationChild();
    }

    public void doDelete() {
        if (generalHelper.getBaseInformationChildService().deleteBaseInformationChild(currentBaseInformationChild)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newBaseInformationChild = new BaseInformationChild();
    }

    public void doAdd() {
        if (newBaseInformationChild.getParent() != null) {
            if (generalHelper.getBaseInformationChildService().exist(newBaseInformationChild.getEnglishName(), newBaseInformationChild.getParent().getId())) {
                me.addErrorMessage("projects_exist");
                return;
            }
        } else if (newBaseInformationChild.getParent() == null) {
            if (generalHelper.getBaseInformationChildService().existwitoutParent(newBaseInformationChild.getEnglishName())) {
                me.addErrorMessage("projects_exist");
                return;
            }
        }
        newBaseInformationChild.setDeleted("0");
        newBaseInformationChild.setEventDate(new Date().toString());
        newBaseInformationChild.setEventType(EventType.CREATE);
        newBaseInformationChild = generalHelper.getBaseInformationChildService().createBaseInformationChilds(newBaseInformationChild);
        if (newBaseInformationChild != null) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }


    public void edit() {

    }

    public void doEdit() {
        if (generalHelper.getBaseInformationChildService().editBaseInformationChild(currentBaseInformationChild) != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }

    public List<BaseInformationChild> completeBaseInformationParents(String query) {
        List<BaseInformationChild> allBaseInformationParents = generalHelper.getBaseInformationChildService().getAllBaseInformationChild();
        List<BaseInformationChild> filteredBaseInformationParents = new ArrayList<BaseInformationChild>();

        for (int i = 0; i < allBaseInformationParents.size(); i++) {
            BaseInformationChild baseInformationParent = allBaseInformationParents.get(i);
            if (baseInformationParent.getEnglishName().toLowerCase().startsWith(query) || baseInformationParent.getPersianName().toLowerCase().startsWith(query)) {
                filteredBaseInformationParents.add(baseInformationParent);
            }
        }

        return filteredBaseInformationParents;
    }

    public BaseInformationChild findById(String id) {
        return generalHelper.getBaseInformationChildService().findById(id);
    }

    public List<BaseInformationChild> getBaseInformationChildList() {
        return baseInformationChildList;
    }

    public void setBaseInformationChildList(List<BaseInformationChild> baseInformationChildList) {
        this.baseInformationChildList = baseInformationChildList;
    }

    public BaseInformationChild getCurrentBaseInformationChild() {
        return currentBaseInformationChild;
    }

    public void setCurrentBaseInformationChild(BaseInformationChild currentBaseInformationChild) {
        this.currentBaseInformationChild = currentBaseInformationChild;
    }

    public BaseInformationChild getNewBaseInformationChild() {
        return newBaseInformationChild;
    }

    public void setNewBaseInformationChild(BaseInformationChild newBaseInformationChild) {
        this.newBaseInformationChild = newBaseInformationChild;
    }

    public BaseInformationParent getSelectedBaseInformationParent() {
        return selectedBaseInformationParent;
    }

    public void setSelectedBaseInformationParent(BaseInformationParent selectedBaseInformationParent) {
        this.selectedBaseInformationParent = selectedBaseInformationParent;
    }
}
