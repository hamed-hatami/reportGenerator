package ir.dotin.core.converter;


import ir.dotin.core.utils.ManagedBeanManager;
import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.LangUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "farsiEnglishNumberCommaConverter")
public class FarsiEnglishNumberCommaConverter implements Converter {
    private UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);
    private PersianNumberCommaConverter persianNumberCommaConverter = new PersianNumberCommaConverter();
    private EnglishNumberCommaConverter englishNumberCommaConverter = new EnglishNumberCommaConverter();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (me.getLocale().equals(LangUtils.LOCALE_FARSI)) {
            return persianNumberCommaConverter.getAsObject(context, component, value);
        } else {
            return englishNumberCommaConverter.getAsObject(context, component, value);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (me.getLocale().equals(LangUtils.LOCALE_FARSI)) {
            return persianNumberCommaConverter.getAsString(context, component, value);
        } else {
            return englishNumberCommaConverter.getAsString(context, component, value);

        }
    }
}
