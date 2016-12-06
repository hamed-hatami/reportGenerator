package ir.dotin.core.action.baseInformation;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
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
@Named("baseInformationParentAction")
@SessionScoped
public class BaseInformationParentAction implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    private List<BaseInformationParent> baseInformationParentList = null;
    private BaseInformationParent currentBaseInformationParent = null;
    private BaseInformationParent newBaseInformationParent = null;

    public String begin() {
        refresh();
        return "list-baseInformationParent";
    }

    private void refresh() {
        baseInformationParentList = generalHelper.getBaseInformationParentService().getAllBaseInformationParent();
    }

    public void doDelete() {
        if (generalHelper.getBaseInformationParentService().deleteBaseInformationParent(currentBaseInformationParent)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newBaseInformationParent = new BaseInformationParent();
    }

    public void doAdd() {

        if (generalHelper.getBaseInformationParentService().exist(newBaseInformationParent.getEnglishName())) {
            me.addErrorMessage("projects_exist");
            return;
        } else {
            newBaseInformationParent.setDeleted("0");
            newBaseInformationParent.setEventDate(new Date().toString());
            newBaseInformationParent.setEventType(EventType.CREATE);
            newBaseInformationParent = generalHelper.getBaseInformationParentService().createBaseInformationParent(newBaseInformationParent);
            if (newBaseInformationParent != null) {
                refresh();
                me.addInfoMessage("operation.occurred");
            } else {
                me.addErrorMessage("operation.not.occurred");
            }
        }

    }

    public void edit() {

    }

    public void doEdit() {
        if (generalHelper.getBaseInformationParentService().editBaseInformationParent(currentBaseInformationParent) != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }

    public BaseInformationParent findById(String id) {
        return generalHelper.getBaseInformationParentService().findById(id);
    }

    public List<BaseInformationParent> completeBaseInformationParents(String query) {
        List<BaseInformationParent> allBaseInformationParents = generalHelper.getBaseInformationParentService().getAllBaseInformationParent();
        List<BaseInformationParent> filteredBaseInformationParents = new ArrayList<BaseInformationParent>();

        for (int i = 0; i < allBaseInformationParents.size(); i++) {
            BaseInformationParent baseInformationParent = allBaseInformationParents.get(i);
            if (baseInformationParent.getEnglishName().toLowerCase().startsWith(query) || baseInformationParent.getPersianName().toLowerCase().startsWith(query)) {
                filteredBaseInformationParents.add(baseInformationParent);
            }
        }

        return filteredBaseInformationParents;
    }

    public List<BaseInformationParent> getBaseInformationParentList() {
        return baseInformationParentList;
    }

    public void setBaseInformationParentList(List<BaseInformationParent> baseInformationParentList) {
        this.baseInformationParentList = baseInformationParentList;
    }

    public BaseInformationParent getCurrentBaseInformationParent() {
        return currentBaseInformationParent;
    }

    public void setCurrentBaseInformationParent(BaseInformationParent currentBaseInformationParent) {
        this.currentBaseInformationParent = currentBaseInformationParent;
    }

    public BaseInformationParent getNewBaseInformationParent() {
        return newBaseInformationParent;
    }

    public void setNewBaseInformationParent(BaseInformationParent newBaseInformationParent) {
        this.newBaseInformationParent = newBaseInformationParent;
    }
}
