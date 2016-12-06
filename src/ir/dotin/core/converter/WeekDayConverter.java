package ir.dotin.core.converter;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "weekDayConverter")
public class WeekDayConverter implements Converter {


    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return value;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
        if (object instanceof String) {
            String value = (String) object;
            switch (value.charAt(0)) {
                case '1':
                    return me.getBundleMessage("saturday");
                case '2':
                    return me.getBundleMessage("sunday");
                case '3':
                    return me.getBundleMessage("monday");
                case '4':
                    return me.getBundleMessage("saturday");
                case '5':
                    return me.getBundleMessage("wednesday");
                case '6':
                    return me.getBundleMessage("thursday");
                case '7':
                    return me.getBundleMessage("friday");
            }
        }
        return "";
    }
}