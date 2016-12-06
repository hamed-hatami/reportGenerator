package ir.dotin.core.converter;


import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.LangUtils;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "farsiEnglishNumberConverter")
public class FarsiEnglishNumberConverter implements Converter {
    private UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
    private FarsiNumberConverter farsiNumberConverter = new FarsiNumberConverter();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (me.getLocale().equals(LangUtils.LOCALE_FARSI)) {
            return farsiNumberConverter.getAsObject(context, component, value);
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (me.getLocale().equals(LangUtils.LOCALE_FARSI)) {
            return farsiNumberConverter.getAsString(context, component, value);
        }
        return String.valueOf(value);
    }
}
