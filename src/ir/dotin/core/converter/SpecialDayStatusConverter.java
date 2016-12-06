package ir.dotin.core.converter;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "specialDayStatusConverter")
public class SpecialDayStatusConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return value;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
        if (object instanceof String) {
            String value = (String) object;
            switch (value.charAt(0)) {
                case '0':
                    return me.getBundleMessage("passive");
                case '1':
                    return me.getBundleMessage("active");
            }
        }
        return "";
    }
}