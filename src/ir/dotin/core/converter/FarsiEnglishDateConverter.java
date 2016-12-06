package ir.dotin.core.converter;


import ir.dotin.core.utils.ManagedBeanManager;
import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.LangUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "farsiEnglishDateConverter")
public class FarsiEnglishDateConverter implements Converter {
    private UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
    private ShamsiDateConverter shamsiDateConverter = new ShamsiDateConverter();
    private DateConverter dateConverter = new DateConverter();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (me.getLocale().equals(LangUtils.LOCALE_FARSI)) {
            return shamsiDateConverter.getAsObject(context, component, value);
        } else {
            return dateConverter.getAsObject(context, component, value);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (me.getLocale().equals(LangUtils.LOCALE_FARSI)) {
            return shamsiDateConverter.getAsString(context, component, value);
        } else {
            return dateConverter.getAsString(context, component, value);

        }
    }
}
