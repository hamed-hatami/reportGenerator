package ir.dotin.core.converter;


import ir.dotin.core.utils.ManagedBeanManager;
import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.utils.LangUtils;
import org.apache.commons.lang3.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "farsiEnglishTimeConverter")
public class FarsiEnglishTimeConverter implements Converter {
    private UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        value = LangUtils.getEnglishNumber(value);
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String value = (String) object;
        if (me.getLocale().equals(LangUtils.LOCALE_FARSI)) {
            if (value != null && !StringUtils.isEmpty(value) && !value.equalsIgnoreCase("null")) {
                value = value.substring(0, 2) + ":" + value.substring(2);
            }


            StringBuffer farsiNumberString = new StringBuffer();
            String number = "";
            number = (String) value;
            for (int i = 0; i < number.length(); ++i) {
                char c = number.charAt(i);
                switch (c) {
                    case '0':
                        farsiNumberString.append('۰');
                        break;
                    case '1':
                        farsiNumberString.append('۱');
                        break;
                    case '2':
                        farsiNumberString.append('۲');
                        break;
                    case '3':
                        farsiNumberString.append('۳');
                        break;
                    case '4':
                        farsiNumberString.append('۴');
                        break;
                    case '5':
                        farsiNumberString.append('۵');
                        break;
                    case '6':
                        farsiNumberString.append('۶');
                        break;
                    case '7':
                        farsiNumberString.append('۷');
                        break;
                    case '8':
                        farsiNumberString.append('۸');
                        break;
                    case '9':
                        farsiNumberString.append('۹');
                        break;
                    default:
                        farsiNumberString.append(c);
                }
            }
            return farsiNumberString.toString();
        } else {
            if (value != null && !StringUtils.isEmpty(value) && !value.equalsIgnoreCase("null")) {
                value = value.substring(0, 2) + ":" + value.substring(2);
            }
            return LangUtils.getEnglishNumber(value);
        }
    }
}
