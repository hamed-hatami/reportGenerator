package ir.dotin.core.converter;

import org.apache.commons.lang3.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "timeConverter")
public class TimeConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty() && value.contains(":") && !value.equalsIgnoreCase("null")) {
            value = value.replace(":", "");
        }
        return value;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if (object instanceof String) {
            String value = (String) object;
            if (value != null && !StringUtils.isEmpty(value) && !value.contains(":") && !value.equalsIgnoreCase("null")) {
                if (value.length() == 4)
                    return value.substring(0, 2) + ":" + value.substring(2, 4);

                if(value.length() == 6)
                    return value.substring(0,2) + ":" + value.substring(2,4) + ":" + value.substring(4,6);
            }
        }
        return "";
    }
}