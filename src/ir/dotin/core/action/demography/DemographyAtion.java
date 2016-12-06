package ir.dotin.core.action.demography;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.action.actualCustomer.ActualCustomerAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.Demography;
import ir.dotin.core.utils.Configuration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by h.safyari on 11/14/2016.
 */
@Named("demographyAtion")
@SessionScoped
public class DemographyAtion implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    @Inject
    private ActualCustomerAction actualCustomerAction;

    private Demography demography = null;
    private Demography currentDemography = new Demography();
    private Demography newDemography = new Demography();
    private Boolean disableChildPanel = true;

    public void begin() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        } else refresh();
    }

    private void refresh() {
        newDemography = actualCustomerAction.getCurrentActualCustomer().getDemography();
        if (newDemography == null) {
            newDemography = new Demography();
        }
    }

    public void doDelete() {
        if (generalHelper.getDemographyService().deleteDemography(currentDemography)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newDemography = new Demography();
    }

    public void doAdd() {

        newDemography.setActualCustomer(actualCustomerAction.getCurrentActualCustomer());
        actualCustomerAction.getCurrentActualCustomer().setDemography(newDemography);
        newDemography.setDeleted("0");
        newDemography.setEventDate(new Date().toString());
        newDemography.setEventType(EventType.CREATE);
        newDemography = generalHelper.getDemographyService().editDemography(newDemography);
        if (newDemography != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }

    }

    public void edit() {

    }

    public void doEdit() {
        if (generalHelper.getDemographyService().editDemography(currentDemography) != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }

    public void educationChange() {
        newDemography.setEductionGroup("0");
        newDemography.setStudy("0");
    }

    public void eductionGroupChange() {
        newDemography.setStudy("0");
    }

    public void matrimonyChange(ValueChangeEvent event) {
        String id = (String) event.getNewValue();
        if(id.equals(Configuration.getProperty("single"))){
            disableChildPanel = false;
            newDemography.setNumberOfChildren(null);
            newDemography.setDateOfMarriage("");
        }
        else{
            disableChildPanel = true;
        }
    }

    public Demography getDemography() {
        return demography;
    }

    public void setDemography(Demography demography) {
        this.demography = demography;
    }

    public Demography getCurrentDemography() {
        return currentDemography;
    }

    public void setCurrentDemography(Demography currentDemography) {
        this.currentDemography = currentDemography;
    }

    public Demography getNewDemography() {
        return newDemography;
    }

    public void setNewDemography(Demography newDemography) {
        this.newDemography = newDemography;
    }

    public Boolean getDisableChildPanel() {
        return disableChildPanel;
    }

    public void setDisableChildPanel(Boolean disableChildPanel) {
        this.disableChildPanel = disableChildPanel;
    }
}
