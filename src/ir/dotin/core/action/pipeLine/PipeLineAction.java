package ir.dotin.core.action.pipeLine;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.action.actualCustomer.ActualCustomerAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.PipeLine;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mr_safiary on 11/25/2016.
 */

@Named("pipeLineAction")
@SessionScoped
public class PipeLineAction implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    @Inject
    private ActualCustomerAction actualCustomerAction;

    private PipeLine pipeLine = null;
    private PipeLine currentPipeLine = new PipeLine();
    private PipeLine newPipeLine = new PipeLine();

    public void begin() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        } else refresh();
    }

    private void refresh() {
        newPipeLine = actualCustomerAction.getCurrentActualCustomer().getPipeLine();
        if (newPipeLine == null) {
            newPipeLine = new PipeLine();
        }
    }

    public void doDelete() {
        if (generalHelper.getPipeLineService().deletePipeLine(currentPipeLine)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newPipeLine = new PipeLine();
    }

    public void doAdd() {

        newPipeLine.setActualCustomer(actualCustomerAction.getCurrentActualCustomer());
        actualCustomerAction.getCurrentActualCustomer().setPipeLine(newPipeLine);
        newPipeLine.setDeleted("0");
        newPipeLine.setEventDate(new Date().toString());
        newPipeLine.setEventType(EventType.CREATE);
        newPipeLine = generalHelper.getPipeLineService().editPipeLine(newPipeLine);
        if (newPipeLine != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }

    }

    public void edit() {

    }

    public void doEdit() {
        if (generalHelper.getPipeLineService().editPipeLine(currentPipeLine) != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }

    public ActualCustomerAction getActualCustomerAction() {

        return actualCustomerAction;
    }

    public void setActualCustomerAction(ActualCustomerAction actualCustomerAction) {
        this.actualCustomerAction = actualCustomerAction;
    }

    public PipeLine getPipeLine() {
        return pipeLine;
    }

    public void setPipeLine(PipeLine pipeLine) {
        this.pipeLine = pipeLine;
    }

    public PipeLine getCurrentPipeLine() {
        return currentPipeLine;
    }

    public void setCurrentPipeLine(PipeLine currentPipeLine) {
        this.currentPipeLine = currentPipeLine;
    }

    public PipeLine getNewPipeLine() {
        return newPipeLine;
    }

    public void setNewPipeLine(PipeLine newPipeLine) {
        this.newPipeLine = newPipeLine;
    }
}
