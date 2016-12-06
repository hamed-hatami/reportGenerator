package ir.dotin.core.action.address;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.action.actualCustomer.ActualCustomerAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.ActualCustomer;
import ir.dotin.core.model.entity.Address;
import ir.dotin.core.model.entity.State;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by h.safyari on 11/14/2016.
 */

@Named("addressAction")
@SessionScoped
public class AddressAction implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    @Inject
    private ActualCustomerAction actualCustomerAction;

    private List<Address> addressList = null;
    private Address currentAddress = new Address();
    private Address newAddress = new Address();

    private String stateId;
    private String cityId;


    @PostConstruct
    public void begin() {
        refresh();
    }

    private void refresh() {
        addressList = generalHelper.getAddressService().findByCustomerId(actualCustomerAction.getCurrentActualCustomer().getId());
    }

    public void doDelete() {
        currentAddress.setActualCustomer(null);
        if (generalHelper.getAddressService().deleteAddress(currentAddress)) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void add() {
        init();
        newAddress = new Address();
    }

    public void init() {
        stateId = "0";
        cityId = "0";
    }

    public void doAdd() {

        if (generalHelper.getAddressService().exist(newAddress.getPostalCode(), actualCustomerAction.getCurrentActualCustomer().getId())) {
            me.addErrorMessage("address_exist");
            return;
        } else {
            newAddress.setActualCustomer(actualCustomerAction.getCurrentActualCustomer());
            newAddress.setDeleted("0");
            newAddress.setEventDate(new Date().toString());
            newAddress.setEventType(EventType.CREATE);
            newAddress = generalHelper.getAddressService().createAddress(newAddress);
            if (newAddress != null) {
                refresh();
                me.addInfoMessage("operation.occurred");
            } else {
                me.addErrorMessage("operation.not.occurred");
            }
        }

    }

    public void edit() {
        stateId = String.valueOf(currentAddress.getCity().getState().getId());
        cityId = String.valueOf(currentAddress.getCity().getId());
    }

    public void doEdit() {
        if (generalHelper.getAddressService().editAddress(currentAddress) != null) {
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }


    public void selectOneStateListener(ValueChangeEvent event) {
        stateId = (String) event.getNewValue();
        cityId = "0";

    }

    public void selectOneCityListener(ValueChangeEvent event) {
        cityId = (String) event.getNewValue();
        newAddress.setCity(generalHelper.getCityService().findById(cityId));
        currentAddress.setCity(generalHelper.getCityService().findById(cityId));
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Address getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(Address newAddress) {
        this.newAddress = newAddress;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
