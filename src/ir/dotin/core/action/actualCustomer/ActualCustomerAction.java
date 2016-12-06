package ir.dotin.core.action.actualCustomer;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.ActualCustomer;
import ir.dotin.core.model.entity.Interests;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Named("actualCustomerAction")
@SessionScoped
public class ActualCustomerAction implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    private List<ActualCustomer> actualCustomerList = null;
    private ActualCustomer currentActualCustomer = null;
    private ActualCustomer newActualCustomer = null;

    private String firstName;
    private String lastName;
    private String customerNumber;
    private String nationalCode;


    private Integer activeIndex=0;

    public String begin() {
        refresh();
        return "list-actualCustomer";
    }

    private void refresh() {
        actualCustomerList = generalHelper.getActualCustomerService().getAllActualCustomer();
    }

    public void doDelete() {
        if (generalHelper.getActualCustomerService().deleteActualCustomer(currentActualCustomer)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        newActualCustomer = new ActualCustomer();
    }

    public void doAdd() {

        if (generalHelper.getActualCustomerService().exist(newActualCustomer.getNationalCode())) {
            me.addErrorMessage("projects_exist");
            return;
        } else {
            newActualCustomer.setDeleted("0");
            newActualCustomer.setEventDate(new Date().toString());
            newActualCustomer.setEventType(EventType.CREATE);
            newActualCustomer = generalHelper.getActualCustomerService().createActualCustomer(newActualCustomer);
            if (newActualCustomer != null) {
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
        if (generalHelper.getActualCustomerService().editActualCustomer(currentActualCustomer) != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }

    public void search() {
        actualCustomerList = generalHelper.getActualCustomerService().search(Arrays.asList("customer_number," + customerNumber, "first_name," + firstName, "last_name," + lastName, "national_code," + nationalCode));
    }

    public String extraInformation() {
        return "extra-actualCustomer";
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        activeIndex = tv.getActiveIndex();
    }

    public List<ActualCustomer> getActualCustomerList() {
        return actualCustomerList;
    }

    public void setActualCustomerList(List<ActualCustomer> actualCustomerList) {
        this.actualCustomerList = actualCustomerList;
    }

    public ActualCustomer getCurrentActualCustomer() {
        return currentActualCustomer;
    }

    public void setCurrentActualCustomer(ActualCustomer currentActualCustomer) {
        this.currentActualCustomer = currentActualCustomer;
    }

    public ActualCustomer getNewActualCustomer() {
        return newActualCustomer;
    }

    public void setNewActualCustomer(ActualCustomer newActualCustomer) {
        this.newActualCustomer = newActualCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }
}
